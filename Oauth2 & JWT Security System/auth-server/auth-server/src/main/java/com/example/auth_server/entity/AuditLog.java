package com.example.auth_server.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "tenant_id")
	private Long tenantId;
	
	@Column(name = "event_type")
	private String eventType;
	
	@Column(name = "ip_address")
	private String ipAddress;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsernaame() {
		return username;
	}

	public void setUsernaame(String usernaame) {
		this.username = usernaame;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
