package com.example.cdc_synchronization_engine.service;

import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;

public interface SynchronizationStatusService {
    void markSuccess(CDCEvent event);

    void markFailure(CDCEvent event);

}
