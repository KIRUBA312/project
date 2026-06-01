package com.example.stocktradingengine.dto;

public class ApiResponseDto {

	private String message;
	private Integer status;
	
	public ApiResponseDto() {}
	
	public ApiResponseDto(String message, Integer status) {
		this.message = message;
		this.status = status;
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
	
	
}
