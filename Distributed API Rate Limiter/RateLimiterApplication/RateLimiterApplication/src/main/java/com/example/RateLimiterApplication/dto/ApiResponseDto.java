package com.example.RateLimiterApplication.dto;

import java.time.LocalDateTime;

public class ApiResponseDto {
	
	private String message;
	private Integer status;
	private LocalDateTime timestamp;
	
	public ApiResponseDto() {}

	public ApiResponseDto(String message, Integer status, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
