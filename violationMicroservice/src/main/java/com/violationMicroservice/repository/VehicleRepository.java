package com.violationMicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.violationMicroservice.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
	
	Optional<Vehicle> findByPlateNumber(String plateNumber);
	
}
