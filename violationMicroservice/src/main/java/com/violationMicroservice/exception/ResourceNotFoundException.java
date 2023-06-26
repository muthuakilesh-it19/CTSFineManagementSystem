package com.violationMicroservice.exception;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	public ResourceNotFoundException() {}

	public ResourceNotFoundException (String message) {
		
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
}
