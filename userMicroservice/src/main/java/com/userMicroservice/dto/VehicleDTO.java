package com.userMicroservice.dto;

import com.userMicroservice.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
private long vehicleId;
	
	
	private String make;
	
	
	private String model;
	
	
	private Integer year;
	
	private String plateNumber;
	
	
}
