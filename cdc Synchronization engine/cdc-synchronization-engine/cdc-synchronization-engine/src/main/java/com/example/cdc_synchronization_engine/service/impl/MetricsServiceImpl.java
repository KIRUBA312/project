package com.example.cdc_synchronization_engine.service.impl;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.dto.ApiStatusResponse;
import com.example.cdc_synchronization_engine.service.MetricsService;

@Service
public class MetricsServiceImpl
        implements MetricsService {

    private final AtomicLong totalProcessed = new AtomicLong();

    private final AtomicLong totalSuccess = new AtomicLong();

    private final AtomicLong totalFailure =new AtomicLong();

    public void incrementSuccess() {

        totalProcessed.incrementAndGet();
        totalSuccess.incrementAndGet();
    }

    public void incrementFailure() {

        totalProcessed.incrementAndGet();
        totalFailure.incrementAndGet();
    }

    @Override
    public long getTotalProcessed() {

        return totalProcessed.get();

    }

    @Override
    public long getTotalSuccess() {

        return totalSuccess.get();

    }

    @Override
    public long getTotalFailure() {

        return totalFailure.get();

    }

    @Override
    public double getSuccessRate() {

        if (totalProcessed.get() == 0) {

            return 0;

        }

        return (double) totalSuccess.get()
                / totalProcessed.get()
                * 100;

    }

    @Override
    public double getFailureRate() {

        if (totalProcessed.get() == 0) {

            return 0;

        }

        return (double) totalFailure.get()
                / totalProcessed.get()
                * 100;

    }

    @Override
    public ApiStatusResponse getApplicationStatus() {

        ApiStatusResponse response =
                new ApiStatusResponse();

        response.setServiceName(
                "CDC Synchronization Engine");

        response.setApplicationStatus(
                "RUNNING");

        response.setKafkaStatus(
                "CONNECTED");

        response.setRedisStatus(
                "CONNECTED");

        response.setElasticsearchStatus(
                "CONNECTED");

        response.setDatabaseStatus(
                "CONNECTED");

        response.setServerTime(
                LocalDateTime.now());

        return response;

    }

}