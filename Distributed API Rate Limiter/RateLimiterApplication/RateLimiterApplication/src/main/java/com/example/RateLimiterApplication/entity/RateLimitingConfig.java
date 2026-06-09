package com.example.RateLimiterApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "rate_limit_config")
public class RateLimitingConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "api_name")
	private String apiName;
	
	@Column(name = "endpoint")
	private String endpoint;
	
	@Column(name = "request_limit")
	private Integer requestLimit;
	
	@Column(name = "window_seconds")
	private Integer windowSeconds;
	
	@Column(name = "premium_limit")
	private Integer premiumLimit;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	public RateLimitingConfig() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public Integer getRequestLimit() {
		return requestLimit;
	}

	public void setRequestLimit(Integer requestLimit) {
		this.requestLimit = requestLimit;
	}

	public Integer getWindowSeconds() {
		return windowSeconds;
	}

	public void setWindowSeconds(Integer windowSeconds) {
		this.windowSeconds = windowSeconds;
	}

	public Integer getPremiumLimit() {
		return premiumLimit;
	}

	public void setPremiumLimit(Integer premiumLimit) {
		this.premiumLimit = premiumLimit;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
