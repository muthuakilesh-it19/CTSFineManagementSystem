package com.violationMicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException ex) {
		return new ResponseEntity<Object>("Resource Not found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=ResourceAlreadyExistException.class)
	public ResponseEntity<Object> resourceAlreadyExists(ResourceAlreadyExistException ex) {
		return new ResponseEntity<Object>("Resource already exists",HttpStatus.CONFLICT);
	}
}
