package com.example.bankingsystem.dto;

import java.time.LocalDateTime;

public class AuditLogResponseDto {

	private Long id;
	private String action;
	private String performedBy;
	private LocalDateTime performedAt;
	
	public AuditLogResponseDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public LocalDateTime getPerformedAt() {
		return performedAt;
	}

	public void setPerformedAt(LocalDateTime performedAt) {
		this.performedAt = performedAt;
	}
	
	
	
}
