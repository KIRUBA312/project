package com.example.multiregion_resilience.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FailoverRequest {

    @NotBlank(message = "Source region is required")
    @Size(
            max = 50,
            message = "Source region cannot exceed 50 characters"
    )
    private String sourceRegion;


    @NotBlank(message = "Target region is required")
    @Size(
            max = 50,
            message = "Target region cannot exceed 50 characters"
    )
    private String targetRegion;


    @NotBlank(message = "Reason is required")
    @Size(
            max = 500,
            message = "Reason cannot exceed 500 characters"
    )
    private String reason;


    public FailoverRequest() {
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


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}
    
    
    
}
