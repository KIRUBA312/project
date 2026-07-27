package com.example.multiregion_resilience.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

	private LocalDateTime timestamp;

    private int status;

    private String errorCode;

    private String message;

    private String path;

    private String requestId;


    public ErrorResponse() {
    }


	public ErrorResponse(LocalDateTime timestamp, int status, String errorCode, String message, String path,
			String requestId) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
		this.path = path;
		this.requestId = requestId;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
    
    
}
