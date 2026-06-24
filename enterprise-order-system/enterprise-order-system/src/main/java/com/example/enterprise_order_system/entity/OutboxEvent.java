package com.example.enterprise_order_system.entity;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "outbox_events")
public class OutboxEvent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "aggregate_type")
	private String aggregateType;
	
	@Column(name = "aggregate_id")
	private Long aggregateId;
	
	@Column(name = "event_type")
	private String eventType;
	
	@Column(name = "payload", columnDefinition = "TEXT")
	private String payload;
	
	@Column(name = "status")
	private String status;
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public Long getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(Long aggregateId) {
		this.aggregateId = aggregateId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	
	
	
	

}
