package com.fleet.driver.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverModel, String> {
    Optional<DriverModel> findByVehicleId(String vehicleId);
}
