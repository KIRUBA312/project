package com.example.cdc_synchronization_engine.service;

import com.example.cdc_synchronization_engine.dto.ApiStatusResponse;

public interface MetricsService {

    ApiStatusResponse getApplicationStatus();

    long getTotalProcessed();

    long getTotalSuccess();

    long getTotalFailure();

    double getSuccessRate();

    double getFailureRate();

}