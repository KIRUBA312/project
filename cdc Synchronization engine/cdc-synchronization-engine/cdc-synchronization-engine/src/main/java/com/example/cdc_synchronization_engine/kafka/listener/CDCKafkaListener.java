package com.example.cdc_synchronization_engine.kafka.listener;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.service.CDCProcessingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CDCKafkaListener {

    private final CDCProcessingService processingService;

    public void process(CDCEvent event) {

        processingService.processEvent(event);

    }

}