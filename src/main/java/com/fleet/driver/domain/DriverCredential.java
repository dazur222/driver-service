package com.fleet.driver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "driver_credentials")
public class DriverCredential {
    @Id @Column(name = "driver_id", length = 64) private String driverId;
    @OneToOne(fetch = FetchType.LAZY, optional = false) @MapsId @JoinColumn(name = "driver_id") private DriverModel driver;
    @Column(length = 60, unique = true, nullable = false)
    private String licenseNumber;
    @Column(length = 40)
    private String licenseCategory;
    private LocalDate licenseIssuedOn;
    private LocalDate licenseExpiresOn;

    protected DriverCredential() { }
    public DriverCredential(String licenseNumber, String licenseCategory, LocalDate licenseIssuedOn, LocalDate licenseExpiresOn) {
        this.licenseNumber = licenseNumber;
        this.licenseCategory = licenseCategory;
        this.licenseIssuedOn = licenseIssuedOn;
        this.licenseExpiresOn = licenseExpiresOn;
    }
    void setDriver(DriverModel driver) { this.driver = driver; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getLicenseCategory() { return licenseCategory; }
    public LocalDate getLicenseIssuedOn() { return licenseIssuedOn; }
    public LocalDate getLicenseExpiresOn() { return licenseExpiresOn; }
}
