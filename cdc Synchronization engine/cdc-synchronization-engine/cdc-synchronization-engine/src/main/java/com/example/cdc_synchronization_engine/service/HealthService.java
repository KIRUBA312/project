package com.example.cdc_synchronization_engine.service;

import com.example.cdc_synchronization_engine.dto.ApiStatusResponse;

public interface HealthService {

	ApiStatusResponse getApplicationHealth();
}
