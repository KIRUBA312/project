package com.example.multiregion_resilience.mapper;

import org.springframework.stereotype.Component;

import com.example.multiregion_resilience.dto.HealthResponse;
import com.example.multiregion_resilience.entity.RegionHealth;

@Component
public class RegionHealthMapper {

	public HealthResponse toResponse(
            RegionHealth regionHealth
    ) {

        if (regionHealth == null) {
            return null;
        }

        HealthResponse response = new HealthResponse();

        response.setRegionId(
                regionHealth.getRegion().getId()
        );

        response.setRegionCode(
                regionHealth.getRegion().getRegionCode()
        );

        response.setRegionName(
                regionHealth.getRegion().getRegionName()
        );

        response.setStatus(
                regionHealth.getStatus()
        );

        response.setResponseTimeMs(
                regionHealth.getResponseTimeMs()
        );

        response.setFailureCount(
                regionHealth.getFailureCount()
        );

        response.setSuccessCount(
                regionHealth.getSuccessCount()
        );

        response.setLastSuccessAt(
                regionHealth.getLastSuccessAt()
        );

        response.setLastFailureAt(
                regionHealth.getLastFailureAt()
        );

        response.setCheckedAt(
                regionHealth.getCheckedAt()
        );

        return response;
    }
}
