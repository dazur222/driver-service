package com.fleet.driver.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CreateDriverRequest(
        @NotBlank @Size(max = 64) @Pattern(regexp = "[A-Za-z0-9._-]+") String id,
        @NotBlank @Size(max = 120) String fullName,
        @NotBlank @Size(max = 60) String licenseNumber,
        @NotBlank @Size(max = 40) String licenseCategory,
        @NotNull @PastOrPresent LocalDate licenseIssuedOn,
        @NotNull @Future LocalDate licenseExpiresOn,
        @NotNull @DecimalMin("0") @DecimalMax("100") @Digits(integer = 3, fraction = 2) BigDecimal safetyScore,
        @NotNull @Size(max = 20) List<@NotBlank @Size(max = 80) String> certifications,
        @Valid CurrentAssignmentRequest currentAssignment) {
}
