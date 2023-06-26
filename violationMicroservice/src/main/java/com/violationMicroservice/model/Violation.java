package com.violationMicroservice.model;

import java.time.LocalDate;
import java.util.Set;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="violations")
public class Violation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="violation_id")
	private Long id;
	
	@NotBlank(message = "vehicle type is required")
	@Column(nullable=false)
	private String violationType;
	
	@NotBlank(message = "date is required")
	@Column(nullable=false)
	private LocalDate date;
	
	@NotBlank(message = "location is required")
	@Column(nullable=false)
	private String location;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;

	
	
	
}
