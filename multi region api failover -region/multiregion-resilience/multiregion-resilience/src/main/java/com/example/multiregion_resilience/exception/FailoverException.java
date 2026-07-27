package com.example.multiregion_resilience.exception;

public class FailoverException extends RuntimeException{

	private final ErrorCode errorCode;
	
	public FailoverException(String message) {
		super(message);
		this.errorCode = ErrorCode.FAILOVER_FAILED;
	}
	
	public FailoverException(ErrorCode errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public FailoverException(ErrorCode errorCode,
			String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	
	
}
