package com.example.multiregion_resilience.entity;

import java.time.LocalDateTime;

import com.example.multiregion_resilience.enums.FailoverType;

import jakarta.persistence.*;

@Entity
@Table(name = "failover_events",
        indexes = {
                @Index(
                        name = "idx_failover_events_created_at",
                        columnList = "created_at"
                ),
                @Index(
                        name = "idx_failover_events_source_region",
                        columnList = "source_region"
                )
        }
)
public class FailoverEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "source_region",nullable = false,length = 50)
	private String sourceRegion;
	
	@Column(name = "target_region",nullable = false,length = 50)
	private String targetRegion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "failover_type",nullable = false, length = 30)
	private FailoverType failoverType;
	
	@Column(name = "reason",length = 500)
	private String reason;
	
	@Column(name = "triggered_by",length = 100)
	private String triggeredBy;
	
	@Column(name = "created_at",nullable = false,
			updatable = false)
	private LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }


    public FailoverEvent() {
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSourceRegion() {
		return sourceRegion;
	}


	public void setSourceRegion(String sourceRegion) {
		this.sourceRegion = sourceRegion;
	}


	public String getTargetRegion() {
		return targetRegion;
	}


	public void setTargetRegion(String targetRegion) {
		this.targetRegion = targetRegion;
	}


	public FailoverType getFailoverType() {
		return failoverType;
	}


	public void setFailoverType(FailoverType failoverType) {
		this.failoverType = failoverType;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getTriggeredBy() {
		return triggeredBy;
	}


	public void setTriggeredBy(String triggeredBy) {
		this.triggeredBy = triggeredBy;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
    
}
