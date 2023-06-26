package com.violationMicroservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.violationMicroservice.model.Vehicle;
import com.violationMicroservice.model.Violation;

@Repository
public interface ViolationRepository extends JpaRepository<Violation,Long>{

	List<Violation> findByVehicleId(Long vehicleId);
	List<Violation> findByVehicle(Vehicle vehicle);
}
