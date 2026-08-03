package com.example.cdc_synchronization_engine.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.service.RetryPublisherService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RetryScheduler {

    private final RetryPublisherService retryPublisherService;

    @Scheduled(fixedDelay = 30000)
    public void retryMessages() {

        retryPublisherService.processRetryQueue();

    }
}