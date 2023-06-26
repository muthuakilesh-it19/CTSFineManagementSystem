package com.fineMicroservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineDTO {
	private Long id;
	private BigDecimal amount;
	private String description;
	private boolean paid;
	private String userName;
	private String plateNumber;
	private LocalDate dateFine;
//	public boolean getPaid() {
//		return this.paid;
//	}
	
}
