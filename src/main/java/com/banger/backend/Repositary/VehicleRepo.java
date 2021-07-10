package com.banger.backend.Repositary;

import com.banger.backend.Entity.Vehicle;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findVehicleByVehicleId(int id);
}
