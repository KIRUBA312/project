package com.example.cdc_synchronization_engine.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.service.ElasticsearchSyncService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ElasticsearchSyncScheduler {

    private final ElasticsearchSyncService syncService;

    @Scheduled(fixedDelay = 30000)
    public void synchronize() {

        syncService.synchronize();

    }

}