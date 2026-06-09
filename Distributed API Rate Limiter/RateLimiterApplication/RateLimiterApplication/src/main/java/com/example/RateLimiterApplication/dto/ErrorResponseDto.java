package com.example.RateLimiterApplication.dto;

import java.time.LocalDateTime;

public class ErrorResponseDto {

	private String error;
	private Integer status;
	private LocalDateTime timestamp;
	
	public ErrorResponseDto() {}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
