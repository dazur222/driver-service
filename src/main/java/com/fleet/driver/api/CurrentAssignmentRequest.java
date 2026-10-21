package com.fleet.driver.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CurrentAssignmentRequest(
        @NotBlank @Size(max = 64) @Pattern(regexp = "[A-Za-z0-9._-]+") String assignmentId,
        @NotBlank @Size(max = 64) @Pattern(regexp = "[A-Za-z0-9._-]+") String vehicleId) {
}
