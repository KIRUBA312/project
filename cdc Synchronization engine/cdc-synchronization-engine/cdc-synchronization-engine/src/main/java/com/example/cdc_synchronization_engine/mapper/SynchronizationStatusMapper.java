package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.SynchronizationStatusResponse;
import com.example.cdc_synchronization_engine.entity.SynchronizationStatus;

@Component
public class SynchronizationStatusMapper {

    public SynchronizationStatusResponse toResponse(SynchronizationStatus entity){

        SynchronizationStatusResponse dto=new SynchronizationStatusResponse();

        dto.setId(entity.getId());
        dto.setEntityName(entity.getEntityName());
        dto.setEntityId(entity.getEntityId());
        dto.setKafkaOffset(entity.getKafkaOffset());
        dto.setElasticsearchDocumentId(entity.getElasticsearchDocumentId());
        dto.setStatus(entity.getStatus());
        dto.setLastSync(entity.getLastSync());

        return dto;
    }
}