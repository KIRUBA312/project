package com.example.multiregion_resilience.dto;

import java.time.LocalDateTime;

import com.example.multiregion_resilience.enums.FailoverType;

public class FailoverResponse {

    private Long id;

    private String sourceRegion;

    private String targetRegion;

    private FailoverType failoverType;

    private String reason;

    private String triggeredBy;

    private LocalDateTime createdAt;


    public FailoverResponse() {
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
