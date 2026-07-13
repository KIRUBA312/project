package com.example.enterprise_iam.exception;

public class ResourceAlreadyExistsException extends RuntimeException{

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}

}
