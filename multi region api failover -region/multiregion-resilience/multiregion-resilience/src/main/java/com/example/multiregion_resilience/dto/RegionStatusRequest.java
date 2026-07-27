package com.example.multiregion_resilience.dto;

import com.example.multiregion_resilience.enums.RegionStatus;

import jakarta.validation.constraints.NotNull;

public class RegionStatusRequest {

	@NotNull(message = "Region status is required")
    private RegionStatus status;

	public RegionStatusRequest() {
		// TODO Auto-generated constructor stub
	}

	public RegionStatus getStatus() {
		return status;
	}

	public void setStatus(RegionStatus status) {
		this.status = status;
	}
	
	
	
}
