package com.example.multiregion_resilience.service;

import java.util.List;

import com.example.multiregion_resilience.dto.HealthResponse;

public interface HealthService {

	List<HealthResponse> getAllRegionHealth();

	HealthResponse getRegionHealth(String regionCode);

}
