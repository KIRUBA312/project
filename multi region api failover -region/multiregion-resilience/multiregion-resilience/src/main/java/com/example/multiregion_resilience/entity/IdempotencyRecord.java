package com.example.multiregion_resilience.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import com.example.multiregion_resilience.enums.IdempotencyStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "idempotency_records",
        indexes = {
                @Index(
                        name = "idx_idempotency_expires_at",
                        columnList = "expires_at"
                )
        }
)
public class IdempotencyRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "idempotency_key",nullable = false,
			unique = true,length = 255)
	private String idempotencyKey;
	
	@Column(name = "request_hash",nullable = false,length = 255)
	private String requestHash;
	
	@Column(name = "response_body",columnDefinition = "TEXT")
	private String responseBody;
	
	@Column(name = "response_status")
	private Integer responseStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 30)
	private IdempotencyStatus status;
	
	@Column(name = "expires_at", nullable = false)
	private LocalDateTime expiresAt;
	
	@Column(name = "created_at",nullable = false,
			updatable = false)
	private LocalDateTime createdAt;
	
	@PrePersist
    protected void onCreate() {

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }


    public IdempotencyRecord() {
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


	public String getRequestHash() {
		return requestHash;
	}


	public void setRequestHash(String requestHash) {
		this.requestHash = requestHash;
	}


	public String getResponseBody() {
		return responseBody;
	}


	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}


	public Integer getResponseStatus() {
		return responseStatus;
	}


	public void setResponseStatus(Integer responseStatus) {
		this.responseStatus = responseStatus;
	}


	public IdempotencyStatus getStatus() {
		return status;
	}


	public void setStatus(IdempotencyStatus status) {
		this.status = status;
	}


	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}


	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
