package com.example.multiregion_resilience.mapper;

import org.springframework.stereotype.Component;

import com.example.multiregion_resilience.dto.RegionRequest;
import com.example.multiregion_resilience.dto.RegionResponse;
import com.example.multiregion_resilience.entity.Region;

@Component
public class RegionMapper {

	public Region toEntity(RegionRequest request) {
        
		if (request == null) {
            return null;
        }

        Region region = new Region();

        region.setRegionCode(
                request.getRegionCode()
        );

        region.setRegionName(
                request.getRegionName()
        );

        region.setEndpointUrl(
                request.getEndpointUrl()
        );

        region.setDeploymentMode(
                request.getDeploymentMode()
        );

        region.setPriority(
                request.getPriority()
        );

        return region;
	}
	
	public RegionResponse toResponse(Region region) {
        
		if (region == null) {
            return null;
        }

        RegionResponse response = new RegionResponse();

        response.setId(
                region.getId()
        );

        response.setRegionCode(
                region.getRegionCode()
        );

        response.setRegionName(
                region.getRegionName()
        );

        response.setEndpointUrl(
                region.getEndpointUrl()
        );

        response.setDeploymentMode(
                region.getDeploymentMode()
        );

        response.setPriority(
                region.getPriority()
        );

        response.setStatus(
                region.getStatus()
        );

        response.setEnabled(
                region.getEnabled()
        );

        response.setCreatedAt(
                region.getCreatedAt()
        );

        response.setUpdatedAt(
                region.getUpdatedAt()
        );

        return response;
	}
	
	 public void updateEntity(
	            Region region,
	            RegionRequest request
	    ) {

	        if (region == null || request == null) {
	            return;
	        }

	        region.setRegionName(
	                request.getRegionName()
	        );

	        region.setEndpointUrl(
	                request.getEndpointUrl()
	        );

	        region.setDeploymentMode(
	                request.getDeploymentMode()
	        );

	        region.setPriority(
	                request.getPriority()
	        );
	    }
}
