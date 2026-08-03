package com.example.cdc_synchronization_engine.service;

import java.util.List;

import com.example.cdc_synchronization_engine.dto.*;
import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;

public interface DeadLetterService {

    void saveDeadLetter(
            CDCEvent event,
            String error);

    List<DeadLetterEventResponse> getAll();

}