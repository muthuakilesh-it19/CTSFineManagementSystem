package com.fineMicroservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFineDTO {
	private Long id;
	private BigDecimal amount;
	private String description;
	private boolean paid;
	private Double penalty;
}
