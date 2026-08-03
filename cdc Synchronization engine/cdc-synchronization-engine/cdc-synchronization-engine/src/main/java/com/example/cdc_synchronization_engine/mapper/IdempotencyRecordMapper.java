package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.IdempotencyRecordResponse;
import com.example.cdc_synchronization_engine.entity.IdempotencyRecord;

@Component
public class IdempotencyRecordMapper {

    public IdempotencyRecordResponse toResponse(IdempotencyRecord entity){

        IdempotencyRecordResponse dto=new IdempotencyRecordResponse();

        dto.setId(entity.getId());
        dto.setEventId(entity.getEventId());
        dto.setTopicName(entity.getTopicName());
        dto.setProcessed(entity.getProcessed());
        dto.setProcessedAt(entity.getProcessedAt());

        return dto;
    }
}
