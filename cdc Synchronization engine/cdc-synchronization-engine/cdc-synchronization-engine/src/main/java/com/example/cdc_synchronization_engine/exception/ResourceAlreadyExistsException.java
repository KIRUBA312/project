package com.example.cdc_synchronization_engine.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
	
	private final ErrorCode errorCode;

	public ResourceAlreadyExistsException(ErrorCode errorCode,
			String message) {
		super(message);
		this.errorCode = errorCode;
	}


	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	

}
