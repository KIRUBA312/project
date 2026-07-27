package com.example.multiregion_resilience.dto;

import java.time.LocalDateTime;

import com.example.multiregion_resilience.enums.DeploymentMode;
import com.example.multiregion_resilience.enums.RegionStatus;

public class RegionResponse {

	private Long id;
    private String regionCode;
    private String regionName;
    private String endpointUrl;
    private DeploymentMode deploymentMode;
    private Integer priority;
    private RegionStatus status;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
	public RegionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	public DeploymentMode getDeploymentMode() {
		return deploymentMode;
	}

	public void setDeploymentMode(DeploymentMode deploymentMode) {
		this.deploymentMode = deploymentMode;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public RegionStatus getStatus() {
		return status;
	}

	public void setStatus(RegionStatus status) {
		this.status = status;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	
	    
}
