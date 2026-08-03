package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.DeadLetterEventResponse;
import com.example.cdc_synchronization_engine.entity.DeadLetterEvent;

@Component
public class DeadLetterEventMapper {

    public DeadLetterEventResponse toResponse(DeadLetterEvent entity){

        DeadLetterEventResponse dto=new DeadLetterEventResponse();

        dto.setId(entity.getId());
        dto.setTopicName(entity.getTopicName());
        dto.setEventKey(entity.getEventKey());
        dto.setPayload(entity.getPayload());
        dto.setErrorMessage(entity.getErrorMessage());
        dto.setRetryCount(entity.getRetryCount());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }
}
