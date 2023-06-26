package com.fineMicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fineMicroservice.model.Fine;

@Repository
public interface FineRepository extends JpaRepository<Fine,Long> {
	List<Fine> findByUserId(Long userId);
	List<Fine> findByvehicleId(Long vehicleId);

}
