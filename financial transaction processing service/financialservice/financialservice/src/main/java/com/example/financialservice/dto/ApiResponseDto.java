package com.example.financialservice.dto;

public class ApiResponseDto {

	private String message;
	private int status;
	public ApiResponseDto() {}
	public ApiResponseDto(String message, int status) {
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
