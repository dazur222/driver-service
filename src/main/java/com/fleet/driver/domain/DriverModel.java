package com.fleet.driver.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "drivers")
public class DriverModel implements Persistable<String> {
    @Id @Column(length = 64) private String id;
    @Column(length = 120)
    private String fullName;
    @Column(precision = 5, scale = 2)
    private BigDecimal safetyScore;
    @Column(name = "assignment_id", unique = true, length = 64) private String assignmentId;
    @Column(name = "vehicle_id", unique = true, length = 64) private String vehicleId;
    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private DriverCredential credential;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "driver_certifications", joinColumns = @JoinColumn(name = "driver_id"))
    @Column(name = "certification", nullable = false, length = 80)
    @OrderColumn(name = "position")
    private List<String> certifications = new ArrayList<>();
    @Transient private boolean fresh = true;

    protected DriverModel() { }

    public DriverModel(String id, String fullName, BigDecimal safetyScore, String assignmentId, String vehicleId, List<String> certifications) {
        this.id = id;
        this.fullName = fullName;
        this.safetyScore = safetyScore;
        this.assignmentId = assignmentId;
        this.vehicleId = vehicleId;
        this.certifications = new ArrayList<>(certifications);
    }

    public void setCredential(DriverCredential credential) { this.credential = credential; credential.setDriver(this); }
    @Override public String getId() { return id; }
    @Override public boolean isNew() { return fresh; }
    public String getFullName() { return fullName; }
    public BigDecimal getSafetyScore() { return safetyScore; }
    public String getAssignmentId() { return assignmentId; }
    public String getVehicleId() { return vehicleId; }
    public DriverCredential getCredential() { return credential; }
    public List<String> getCertifications() { return certifications; }
    @PostLoad @PostPersist void markPersisted() { fresh = false; }
}
