package com.example.cdc_synchronization_engine.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.entity.ProcessingMetric;
import com.example.cdc_synchronization_engine.repository.ProcessingMetricsRepository;
import com.example.cdc_synchronization_engine.service.ProcessingMetricService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProcessingMetricServiceImpl
        implements ProcessingMetricService {

    private final ProcessingMetricsRepository repository;
    private final MetricsServiceImpl metricsService;

    @Override
    public void recordSuccess(
            String topicName,
            long processingTime) {

        ProcessingMetric metric = new ProcessingMetric();

        metric.setTopicName(topicName);

        metric.setProcessedRecords(1L);

        metric.setFailedRecords(0L);

        metric.setProcessingTimeMs(processingTime);

        repository.save(metric);
        
        metricsService.incrementSuccess();
    }

    @Override
    public void recordFailure(
            String topicName,
            long processingTime) {

        ProcessingMetric metric = new ProcessingMetric();

        metric.setTopicName(topicName);

        metric.setProcessedRecords(0L);

        metric.setFailedRecords(1L);

        metric.setProcessingTimeMs(processingTime);

        repository.save(metric);
        metricsService.incrementFailure();
    }

}