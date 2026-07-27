package com.example.multiregion_resilience.dto;

import java.time.LocalDateTime;

import com.example.multiregion_resilience.enums.HealthStatus;

public class HealthResponse {

    private Long regionId;

    private String regionCode;

    private String regionName;

    private HealthStatus status;

    private Long responseTimeMs;

    private Integer failureCount;

    private Integer successCount;

    private LocalDateTime lastSuccessAt;

    private LocalDateTime lastFailureAt;

    private LocalDateTime checkedAt;


    public HealthResponse() {
    }


	public Long getRegionId() {
		return regionId;
	}


	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}


	public String getRegionCode() {
		return regionCode;
	}


	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}


	public String getRegionName() {
		return regionName;
	}


	public void setRegionName(String regionName) {
		this.regionName = regionName;
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
    
    
}
