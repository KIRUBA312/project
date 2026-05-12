package com.example.apigateway.dto;

import java.time.LocalDateTime;

public class ErrorResponseDto {

	private String error;
	private int status;
	private LocalDateTime timestamp;
	
	public ErrorResponseDto() {}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
