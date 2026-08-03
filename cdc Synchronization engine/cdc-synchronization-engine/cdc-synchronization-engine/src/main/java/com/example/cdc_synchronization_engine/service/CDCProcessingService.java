package com.example.cdc_synchronization_engine.service;

import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;

public interface CDCProcessingService {

    void processEvent(CDCEvent event);

}