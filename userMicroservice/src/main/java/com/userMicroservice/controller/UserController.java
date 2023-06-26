package com.userMicroservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.userMicroservice.dto.AuthRequest;
import com.userMicroservice.dto.Payment;
import com.userMicroservice.dto.TokenResponse;
import com.userMicroservice.dto.UserDTO;
import com.userMicroservice.dto.UserRegistrationDTO;
import com.userMicroservice.dto.Violation;
import com.userMicroservice.exception.ResourceAlreadyExistException;
import com.userMicroservice.exception.ResourceNotFoundException;
import com.userMicroservice.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins ="http://localhost:3000")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) throws ResourceAlreadyExistException {
		logger.info("Creating user: {}", userRegistrationDTO);
		ResponseEntity<String> registrationInfo=userService.registerUser(userRegistrationDTO);
		return registrationInfo;
	}
	
	@PostMapping("/authenticate")
	public TokenResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		System.out.println(authRequest.getEmail());

		return userService.authenticate(authRequest);
	}
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) throws ResourceNotFoundException {
        logger.info("Getting user by ID: {}", userId);
        ResponseEntity<UserDTO> resUser=userService.getUserById(userId);
        return resUser;
	}

	@GetMapping("/")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserDTO> getAllUsers() {
        logger.info("Getting all users ");
		List<UserDTO> user = userService.getAllUsers();
		
		System.out.println("users :"+user);
		return user;
	}

	

	

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) throws ResourceNotFoundException {
		logger.info("Deleting user by ID: {}", userId);
		userService.deleteUser(userId);
		return ResponseEntity.ok("User deleted successfully");
	}

	@PutMapping("/{id}")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<String> updateUser(@PathVariable("id") Long userId, @RequestBody UserRegistrationDTO userUpdateDTO)
			throws ResourceNotFoundException {
        logger.info("Updating user with ID {}: {}", userId, userUpdateDTO);
		ResponseEntity<String> updatedUser = userService.updateUser(userId, userUpdateDTO);
		return updatedUser;

	}
	

	String violationUrl = "http://localhost:9091/violationmicroservice";

	/*@GetMapping("/violation/getAllViolations")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Violation[] getAllViolations() {
		logger.info("Getting all violations from user microservice");
		String tempUrl = violationUrl + "/violation/getAllViolations";
		Violation[] response = restTemplate.getForObject(tempUrl, Violation[].class);
		return response;
	}*/

	@GetMapping("/violation/getViolationById{id}")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public Violation getViolationById(@RequestHeader("Authorization")String authorizationHeader, @PathVariable("id") Long vehicleId) {
		//logger.info("Getting violation with id"+violationId+"from user microservice");
		String tempUrl = violationUrl + "/vehicle/{vehicleId}";
		Violation response = restTemplate.getForObject(tempUrl, Violation.class, vehicleId);
		return response;
	}
	
	@PostMapping("/violations/createViolation")
	public String createViolation(@RequestBody Violation violation) {
		logger.info("Creating a new violation using rest template");
		String tempUrl=violationUrl+"/add";
		Violation violationRes=restTemplate.postForObject(tempUrl, violation, Violation.class);
		return("created");
	}
	
	String paymentUrl = "http://localhost:9097/paymentmicroservice";

	@GetMapping("/payment/getAllPayments")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Payment[] getAllPayments() {
		logger.info("Getting all payments from user microservice");
		String tempUrl = paymentUrl + "/payment/getAllPayments";
		Payment[] response = restTemplate.getForObject(tempUrl, Payment[].class);
		return response;
	}

	@GetMapping("/payment/getPaymentById{id}")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public Payment getPaymentById(@PathVariable("id") Long paymentId) {
		logger.info("Getting payment with id"+paymentId+"from user microservice");
		String tempUrl = paymentUrl + "/payment/getPaymentById{id}";
		Payment response = restTemplate.getForObject(tempUrl, Payment.class, paymentId);
		return response;
	}
	@PostMapping("/payment/createPayment")
	public String createPayment(@RequestBody Payment payment) {
		logger.info("creating payment using rest template");
		String tempUrl=paymentUrl+"/add";
		Payment pay=restTemplate.postForObject(tempUrl, payment, Payment.class);
		return "created";
	}
	
	

}
