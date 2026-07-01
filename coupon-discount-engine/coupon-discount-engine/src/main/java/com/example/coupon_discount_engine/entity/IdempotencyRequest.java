package com.example.coupon_discount_engine.entity;

import java.time.LocalDateTime;

import com.example.coupon_discount_engine.enums.RequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "idempotency_request")
public class IdempotencyRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "idempotency_key",nullable = false,unique = true,length = 100)
	private String idempotencyKey;
	@Column(name = "request_hash",columnDefinition = "TEXT")
	private String requesthash;
	@Column(name = "response_data",columnDefinition = "LONGTEXT")
	private String responseData;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RequestStatus status;
	@Column(name = "created_at", insertable = false, updatable = false)
	private LocalDateTime createdAt;
	
	public IdempotencyRequest() {
		
	}

	public IdempotencyRequest(Long id, String idempotencyKey, String requesthash, String responseData, RequestStatus status,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.idempotencyKey = idempotencyKey;
		this.requesthash = requesthash;
		this.responseData = responseData;
		this.status = status;
		this.createdAt = createdAt;
	}

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

	public String getRequesthash() {
		return requesthash;
	}

	public void setRequesthash(String requesthash) {
		this.requesthash = requesthash;
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

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "IdempotencyRequest [id="+id
				+", idempotencyKey="+idempotencyKey
				+",requestHash="+requesthash
				+",responseData="+responseData
				+",status="+status
				+",createdAt="+createdAt+"]";
	}

}
