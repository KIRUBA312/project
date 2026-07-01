package com.example.coupon_discount_engine.dto;

import java.time.LocalDateTime;

import com.example.coupon_discount_engine.enums.RequestStatus;

public class IdempotencyResponseDto {

	private Long id;
	private String idempotencyKey;
	private String requestHash;
	private String responseData;
	private RequestStatus status;
	private LocalDateTime createdAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdempotencyKey() {
		return idempotencyKey;
	}
	public void setIdempotencyKey(String idempotencyKey) {
		this.idempotencyKey = idempotencyKey;
	}
	public String getRequestHash() {
		return requestHash;
	}
	public void setRequestHash(String requestHash) {
		this.requestHash = requestHash;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	public RequestStatus getStatus() {
		return status;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setStatus(RequestStatus status) {
		// TODO Auto-generated method stub
		this.status=status;
		
	}
	
	
	
}
