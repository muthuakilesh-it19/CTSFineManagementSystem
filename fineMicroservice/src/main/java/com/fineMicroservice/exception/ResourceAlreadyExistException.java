package com.fineMicroservice.exception;

public class ResourceAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ResourceAlreadyExistException() {
		super();
	}

	public ResourceAlreadyExistException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


	
	
}
