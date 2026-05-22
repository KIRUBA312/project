package com.example.financialservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_audit")
public class TransactionAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id")
	private Long auditId;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "event_type")
	private String eventType;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	public TransactionAudit() {}

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	

}
