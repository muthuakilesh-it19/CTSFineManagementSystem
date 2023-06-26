package com.userMicroservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	private Long paymentId;
	
	private Double amount;
	
	private LocalDate date;

	private Long userId;
	
	private Fine fine;

	

}
