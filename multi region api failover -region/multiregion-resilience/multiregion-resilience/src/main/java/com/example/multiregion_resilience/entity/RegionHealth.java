package com.example.multiregion_resilience.entity;

import java.time.LocalDateTime;

import com.example.multiregion_resilience.enums.HealthStatus;

import jakarta.persistence.*;

@Entity
@Table( name = "region_health",
        indexes = {
                @Index(
                        name = "idx_region_health_region_id",
                        columnList = "region_id"
                ),
                @Index(
                        name = "idx_region_health_status",
                        columnList = "status"
                )
        }
)
public class RegionHealth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(
			name = "region_id",
			nullable = false
			)
	private Region region;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status",nullable = false, length = 30)
	private HealthStatus status;
	
	@Column(name = "response_time_ms")
	private Long responseTimeMs;
	
	@Column(name = "failure_count",nullable = false)
	private Integer failureCount = 0;
	
	@Column(name = "success_count", nullable = false)
	private Integer successCount = 0;
	
	@Column(name = "last_success_at")
	private LocalDateTime lastSuccessAt;
	
	@Column(name = "last_failure_at")
	private LocalDateTime lastFailureAt;
	
	@Column(name = "checked_at", nullable = false)
	private LocalDateTime checkedAt;
	
	@Column(name = "created_at",nullable = false, 
			updatable = false)
	private LocalDateTime createdAt;
	
	@PrePersist
    protected void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        createdAt = now;

        checkedAt = now;

        if (failureCount == null) {
            failureCount = 0;
        }

        if (successCount == null) {
            successCount = 0;
        }

        if (status == null) {
            status = HealthStatus.UNKNOWN;
        }
    }
	
	@PreUpdate
	protected void onUpdate() {

	    checkedAt =
	            LocalDateTime.now();
	}

	public RegionHealth() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public HealthStatus getStatus() {
		return status;
	}

	public void setStatus(HealthStatus status) {
		this.status = status;
	}

	public Long getResponseTimeMs() {
		return responseTimeMs;
	}

	public void setResponseTimeMs(Long responseTimeMs) {
		this.responseTimeMs = responseTimeMs;
	}

	public Integer getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(Integer failureCount) {
		this.failureCount = failureCount;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public LocalDateTime getLastSuccessAt() {
		return lastSuccessAt;
	}

	public void setLastSuccessAt(LocalDateTime lastSuccessAt) {
		this.lastSuccessAt = lastSuccessAt;
	}

	public LocalDateTime getLastFailureAt() {
		return lastFailureAt;
	}

	public void setLastFailureAt(LocalDateTime lastFailureAt) {
		this.lastFailureAt = lastFailureAt;
	}

	public LocalDateTime getCheckedAt() {
		return checkedAt;
	}

	public void setCheckedAt(LocalDateTime checkedAt) {
		this.checkedAt = checkedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
