package com.example.multiregion_resilience.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.multiregion_resilience.dto.HealthResponse;
import com.example.multiregion_resilience.dto.PageResponse;
import com.example.multiregion_resilience.dto.RegionResponse;
import com.example.multiregion_resilience.enums.HealthStatus;
import com.example.multiregion_resilience.enums.RegionStatus;
import com.example.multiregion_resilience.exception.ErrorCode;
import com.example.multiregion_resilience.exception.InvalidOperationException;
import com.example.multiregion_resilience.service.HealthService;
import com.example.multiregion_resilience.service.RegionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HealthServiceImpl implements HealthService{

	private final RegionService regionService;
	
	public HealthServiceImpl(RegionService regionService) {
		this.regionService = regionService;
	}

	@Override
	public List<HealthResponse> getAllRegionHealth() {
		// TODO Auto-generated method stub
		PageResponse<RegionResponse> pageResponse =
				regionService.getAllRegions(0, 100, null, null);
		
		return pageResponse.getContent().stream()
				.map(this::buildHealthResponse)
				.toList();
	}

	@Override
	public HealthResponse getRegionHealth(String regionCode) {
		// TODO Auto-generated method stub
		if(regionCode == null || regionCode.isBlank()) {
			throw new InvalidOperationException(
					ErrorCode.INVALID_OPERATION, 
					"Region code cannot be empty");
		}
		
		RegionResponse region = regionService
				.getRegionByCode(regionCode.trim()
						.toUpperCase());
		return buildHealthResponse(region);
		
	}

	private HealthResponse buildHealthResponse(
			RegionResponse region) {
		// TODO Auto-generated method stub
		HealthResponse response =new HealthResponse();
        response.setRegionId( region.getId());
        response.setRegionCode(region.getRegionCode());
        response.setRegionName( region.getRegionName());
        if (region.getStatus()== RegionStatus.ACTIVE
                && Boolean.TRUE.equals(
                		region.getEnabled())) {
            response.setStatus( HealthStatus.HEALTHY);
        } else {
        	response.setStatus( HealthStatus.UNHEALTHY);
        }
        response.setResponseTimeMs(null);
        response.setFailureCount(0);
        response.setSuccessCount(0);
        response.setLastSuccessAt(null);
        response.setLastFailureAt(null);
        response.setCheckedAt(LocalDateTime.now());


        return response;
    }

	
}
