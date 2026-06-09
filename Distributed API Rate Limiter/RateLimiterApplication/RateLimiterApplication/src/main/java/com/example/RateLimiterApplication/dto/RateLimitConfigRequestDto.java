package com.example.RateLimiterApplication.dto;

public class RateLimitConfigRequestDto {

	private String apiName;
	private String endpoint;
	private Integer requestLimit;
	private Integer windowSeconds;
	private Integer premiumLimit;
	private Boolean isActive;
	
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
