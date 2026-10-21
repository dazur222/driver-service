package com.fleet.driver.domain;

import com.fleet.driver.api.CreateDriverRequest;
import com.fleet.driver.api.CurrentAssignmentResponse;
import com.fleet.driver.api.DriverNotFoundException;
import com.fleet.driver.api.DriverResponse;
import com.fleet.driver.api.InvalidDriverRequestException;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DriverService {
    private final DriverRepository drivers;
    private final Clock clock;
    public DriverService(DriverRepository drivers, Clock clock) { this.drivers = drivers; this.clock = clock; }

    @Transactional
    public DriverResponse create(CreateDriverRequest request) {
        LocalDate today = LocalDate.now(clock);
        if (request.licenseExpiresOn().isBefore(today.plusDays(1)) || request.licenseExpiresOn().isBefore(request.licenseIssuedOn())) {
            throw new InvalidDriverRequestException("La licencia debe estar vigente y vencer después de su emisión.");
        }
        String assignmentId = request.currentAssignment() == null ? null : request.currentAssignment().assignmentId().strip();
        String vehicleId = request.currentAssignment() == null ? null : request.currentAssignment().vehicleId().strip();
        DriverModel driver = new DriverModel(request.id().strip(), request.fullName().strip(), request.safetyScore(), assignmentId, vehicleId,
                request.certifications().stream().map(String::strip).toList());
        driver.setCredential(new DriverCredential(request.licenseNumber().strip(), request.licenseCategory().strip(), request.licenseIssuedOn(), request.licenseExpiresOn()));
        return response(drivers.saveAndFlush(driver));
    }

    @Transactional(readOnly = true)
    public DriverResponse get(String id) { return response(drivers.findById(id).orElseThrow(() -> new DriverNotFoundException(id))); }

    @Transactional(readOnly = true)
    public DriverResponse getByVehicle(String vehicleId) { return response(drivers.findByVehicleId(vehicleId).orElseThrow(() -> new DriverNotFoundException(vehicleId))); }

    private DriverResponse response(DriverModel driver) {
        DriverCredential credential = driver.getCredential();
        CurrentAssignmentResponse assignment = driver.getVehicleId() == null ? null : new CurrentAssignmentResponse(driver.getAssignmentId(), driver.getVehicleId());
        return new DriverResponse(driver.getId(), driver.getFullName(), credential.getLicenseNumber(), credential.getLicenseCategory(), credential.getLicenseIssuedOn(), credential.getLicenseExpiresOn(), driver.getSafetyScore(), List.copyOf(driver.getCertifications()), assignment);
    }
}
