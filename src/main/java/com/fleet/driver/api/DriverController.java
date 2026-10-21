package com.fleet.driver.api;

import com.fleet.driver.domain.DriverService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService drivers;
    public DriverController(DriverService drivers) { this.drivers = drivers; }
    @PostMapping public ResponseEntity<DriverResponse> create(@Valid @RequestBody CreateDriverRequest request) {
        DriverResponse created = drivers.create(request);
        return ResponseEntity.created(URI.create("/drivers/" + created.id())).body(created);
    }
    @GetMapping("/{id}") public DriverResponse get(@PathVariable String id) { return drivers.get(id); }
    @GetMapping("/by-vehicle/{vehicleId}") public DriverResponse byVehicle(@PathVariable String vehicleId) { return drivers.getByVehicle(vehicleId); }
}
