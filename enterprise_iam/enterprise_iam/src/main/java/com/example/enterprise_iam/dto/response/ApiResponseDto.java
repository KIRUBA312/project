package com.example.enterprise_iam.dto.response;

import java.time.LocalDateTime;

public class ApiResponseDto {

	private Boolean success;
	private String message;
	private Object data;
	private LocalDateTime timestamp;
	
	public ApiResponseDto(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ApiResponseDto(boolean success, String message) {
		// TODO Auto-generated constructor stub
		this.success=success;
		this.message=message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
}
