package com.example.multiregion_resilience.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import com.example.multiregion_resilience.enums.DeploymentMode;
import com.example.multiregion_resilience.enums.RegionStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "regions",
        indexes = {
                @Index(
                        name = "idx_regions_status",
                        columnList = "status"
                ),
                @Index(
                        name = "idx_regions_priority",
                        columnList = "priority"
                ),
                @Index(
                        name = "idx_regions_enabled",
                        columnList = "enabled"
                )
        }
)
public class Region {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(
			name = "region_code",
			nullable = false,
			unique = true,
			length = 50
			)
	private String regionCode;
	
	@Column(name = "region_name",nullable = false,length = 100)
	private String regionName;
	
	@Column(name = "endpoint_url",nullable = false,length = 500)
	private String endpointUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "deployment_mode", nullable = false, length = 30)
	private DeploymentMode deploymentMode;
	
	@Column(name = "priority", nullable = false)
	private Integer priority;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 30)
	private RegionStatus status;
	
	@Column(name = "enabled",nullable = false)
	private Boolean enabled = true;
	
	@Column(name = "created_at",nullable = false,updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at",nullable = false)
	private LocalDateTime updatedAt;
	
	@Version
	@Column(name = "version", nullable = false)
	private Long version;
	
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		createdAt = now;
		updatedAt = now;
		if(enabled == null) {
			enabled = true;
		}
//		if(status == null) {
//			enabled = true;
//		}
		if(status == null) {
			status = RegionStatus.UNKNOWN;
		}
		if(version == null) {
			version = 0L;
		}
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Region() {
		
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	
	
	
	

}
