package com.example.multiregion_resilience.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "audit_logs",
        indexes = {
                @Index(
                        name = "idx_audit_logs_request_id",
                        columnList = "request_id"
                ),
                @Index(
                        name = "idx_audit_logs_created_at",
                        columnList = "created_at"
                ),
                @Index(
                        name = "idx_audit_logs_region",
                        columnList = "region"
                )
        }
)
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "request_id",nullable = false,length = 100)
	private String requestId;
	
	@Column(name = "user_id",length = 100)
	private String userId;
	
	@Column(name = "action",nullable = false,length = 100)
	private String action;
	
	@Column(name = "resource",length = 200)
	private String resource;
	
	@Column(name = "region",length = 50)
	private String region;
	
	@Column(name = "status", length = 30)
	private String status;
	
	@Column(name = "details", columnDefinition = "TEXT")
	private String details;
	
	@Column(name = "created_at",nullable = false,
			updatable = false)
	private LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }


    public AuditLog() {
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getResource() {
		return resource;
	}


	public void setResource(String resource) {
		this.resource = resource;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
    
    
}
