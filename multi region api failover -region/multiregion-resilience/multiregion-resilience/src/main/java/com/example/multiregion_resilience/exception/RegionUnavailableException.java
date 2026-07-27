package com.example.multiregion_resilience.exception;

public class RegionUnavailableException extends 
RuntimeException{

	private final ErrorCode errorCode;
	
	public RegionUnavailableException(String message) {
		super(message);
		this.errorCode = ErrorCode.REGION_UNAVAILABLE;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	
	
}
