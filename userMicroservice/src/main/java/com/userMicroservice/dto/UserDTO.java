package com.userMicroservice.dto;

import java.util.List;

import com.userMicroservice.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String userName;
	private String email;
	private List<String> roles;
	
	private List<VehicleDTO> vehicle;
}
