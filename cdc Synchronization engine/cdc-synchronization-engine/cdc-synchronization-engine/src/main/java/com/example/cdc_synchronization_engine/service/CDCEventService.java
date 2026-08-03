package com.example.cdc_synchronization_engine.service;


import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;


public interface CDCEventService {


    void publishEvent(
            String topic,
            CDCEvent event
    );


}