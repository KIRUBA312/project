package com.example.multiregion_resilience.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CacheSyncRequest {

    @NotBlank(message = "Cache key is required")
    @Size(
            max = 255,
            message = "Cache key cannot exceed 255 characters"
    )
    private String cacheKey;


    @NotBlank(message = "Cache value is required")
    private String cacheValue;


    @NotBlank(message = "Source region is required")
    @Size(
            max = 50,
            message = "Source region cannot exceed 50 characters"
    )
    private String sourceRegion;


    public CacheSyncRequest() {
    }


	public String getCacheKey() {
		return cacheKey;
	}


	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}


	public String getCacheValue() {
		return cacheValue;
	}


	public void setCacheValue(String cacheValue) {
		this.cacheValue = cacheValue;
	}


	public String getSourceRegion() {
		return sourceRegion;
	}


	public void setSourceRegion(String sourceRegion) {
		this.sourceRegion = sourceRegion;
	}
    
    
}
