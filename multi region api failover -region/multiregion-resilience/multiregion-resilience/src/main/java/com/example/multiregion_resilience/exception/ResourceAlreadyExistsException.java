package com.example.multiregion_resilience.exception;

public class ResourceAlreadyExistsException extends RuntimeException{

	private final ErrorCode errorCode;
	
	public ResourceAlreadyExistsException(String message) {
		super(message);
		this.errorCode = ErrorCode.RESOURCE_ALREADY_EXISTS;
	}

	public ResourceAlreadyExistsException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	
	
}
