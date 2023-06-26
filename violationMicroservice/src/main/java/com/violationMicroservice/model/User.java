package com.violationMicroservice.model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	private Long userId;
	
	
	private String userName;
	
	
	private String email;
	
	
	private String password;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)

	private List<Vehicle> vehicles;

	
 
	


	
	
}
