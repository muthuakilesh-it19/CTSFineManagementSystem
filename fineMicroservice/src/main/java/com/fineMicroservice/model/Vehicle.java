package com.fineMicroservice.model;

import java.util.List;

//import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vehicles")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vehicle_id")
	private long id;
	
	//@NotBlank(message = "plate number is required")
	@Column(name="plate_number",nullable=false)
	private String plateNumber;
	
	@OneToMany(mappedBy="vehicle",cascade=CascadeType.ALL)
	private List<Fine> fines;
	
	
}
