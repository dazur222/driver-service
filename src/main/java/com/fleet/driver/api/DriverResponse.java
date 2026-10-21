package com.fleet.driver.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DriverResponse(
        String id, String fullName, String licenseNumber, String licenseCategory,
        LocalDate licenseIssuedOn, LocalDate licenseExpiresOn, BigDecimal safetyScore,
        List<String> certifications, CurrentAssignmentResponse currentAssignment) {
}
