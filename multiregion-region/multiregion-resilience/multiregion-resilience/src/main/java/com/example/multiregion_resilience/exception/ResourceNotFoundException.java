package com.example.multiregion_resilience.exception;

public class ResourceNotFoundException extends RuntimeException{

	private final ErrorCode errorCode;

	public ResourceNotFoundException(String message) {
		super(message);
		this.errorCode = ErrorCode.RESOURCE_NOT_FOUND;
	}

	public ResourceNotFoundException(ErrorCode errorCode,
			String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	
	
	

}
