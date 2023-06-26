package com.userMicroservice.dto;

import java.time.LocalDate;
import java.util.Set;

import com.userMicroservice.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Violation {
	
	private Long violationId;
	
	private String violationType;
	
	private LocalDate date;
	
	private String location;
	
	private Double amount;
		
	private Set<VehicleDTO> vehicle;
	
	
}
