package com.example.cdc_synchronization_engine.service;

public interface ProcessingMetricService {
    void recordSuccess(String topicName, long processingTime);

    void recordFailure(String topicName, long processingTime);
}
