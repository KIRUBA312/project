package com.example.enterprise_iam.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "action",length = 100)
	private String action;
	
	@Column(name = "entity_name",length = 100)
	private String entityName;
	
	@Column(name = "entity_id")
	private Long entityId;
	
	@Column(name = "performed_by")
	private Long performedBy;
	
	@Column(name = "performed_at")
	private LocalDateTime performedAt;
	
	@Lob
	@Column(name = "details")
	private String details;
	
	public AuditLog() {}

	public AuditLog(Long id, String action, String entityName, Long entityId, Long performedBy,
			LocalDateTime performedAt, String details) {
		super();
		this.id = id;
		this.action = action;
		this.entityName = entityName;
		this.entityId = entityId;
		this.performedBy = performedBy;
		this.performedAt = performedAt;
		this.details = details;
	}

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

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(Long performedBy) {
		this.performedBy = performedBy;
	}

	public LocalDateTime getPerformedAt() {
		return performedAt;
	}

	public void setPerformedAt(LocalDateTime performedAt) {
		this.performedAt = performedAt;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	

}
