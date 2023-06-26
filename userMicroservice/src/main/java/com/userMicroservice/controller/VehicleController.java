package com.userMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userMicroservice.dto.VehicleDTO;
import com.userMicroservice.exception.ResourceNotFoundException;
import com.userMicroservice.model.Vehicle;
import com.userMicroservice.service.VehicleServiceImpl;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins ="http://localhost:3000")

public class VehicleController {
	
	@Autowired
	private VehicleServiceImpl vehicleService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<VehicleDTO>> getUserVehicles(@PathVariable Long userId)throws ResourceNotFoundException
	{
		return ResponseEntity.ok(vehicleService.getUserVehicles(userId));
	}
	
	@GetMapping("/{userId}/plate/{plateNumber}")
	public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long userId,@PathVariable String plateNumber)throws ResourceNotFoundException{
		
		return ResponseEntity.ok(vehicleService.getVehicleById(userId,plateNumber));
	}
	
	@PostMapping("/{userId}")
	public ResponseEntity<String> createVehicle(@PathVariable Long userId,@RequestBody VehicleDTO vehicleDTO)throws ResourceNotFoundException{
		
		VehicleDTO vehicle=vehicleService.createVehicle(userId,vehicleDTO);
		return ResponseEntity.ok("vehicle added successfully");
	}
	
	@PutMapping("/{userId}/update/{plateNumber}")
	public ResponseEntity<String> updateVehicle(@PathVariable Long userId,@PathVariable String plateNumber,@RequestBody VehicleDTO vehicleDTO)throws ResourceNotFoundException{
		vehicleService.updateVehicle(userId,plateNumber,vehicleDTO);
		return ResponseEntity.ok("updated successfully");
	}
	
	@DeleteMapping("//{userId}/{plateNumber}")
	public ResponseEntity<String> deleteVehicle(@PathVariable Long userId,@PathVariable String plateNumber)throws ResourceNotFoundException{
		vehicleService.deleteVehicle(userId, plateNumber);
		return ResponseEntity.ok("deleted");
	}
	
	

}
