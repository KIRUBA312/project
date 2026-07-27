package com.example.multiregion_resilience.service;


import com.example.multiregion_resilience.dto.PageResponse;
import com.example.multiregion_resilience.dto.RegionRequest;
import com.example.multiregion_resilience.dto.RegionResponse;
import com.example.multiregion_resilience.dto.RegionStatusRequest;
import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.enums.RegionStatus;

import jakarta.validation.Valid;

public interface RegionService {

	RegionResponse createRegion(@Valid RegionRequest request);

	RegionResponse getRegionById(Long id);

	RegionResponse getRegionByCode(String regioncode);

	PageResponse<RegionResponse> getAllRegions(int page, int size, RegionStatus status, Boolean enabled);

	RegionResponse updateRegion(Long id, @Valid RegionRequest request);

	RegionResponse updateRegionStatus(Long id, @Valid RegionStatusRequest request);

	void deleteRegion(Long id);

	Region getRegionEntityByCode(String sourceRegionCode);

}
