package com.violationMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.violationMicroservice.model.User;



@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	//List<User> findByFirstName(String firstName);
	
	//List<User> findByLastName(String lastName);
	
	//List<User> findByEmail(String email);
	
	
}
