package com.violationMicroservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolationDTO {
	
	private Long id;
	
	
	private String violationType;
	
	
	private LocalDate date;
	
	
	private String location;
	
	private String plateNumber;
	
}
