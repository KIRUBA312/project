package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.KafkaProcessingStatusResponse;
import com.example.cdc_synchronization_engine.entity.KafkaProcessingStatus;

@Component
public class KafkaProcessingStatusMapper {

    public KafkaProcessingStatusResponse toResponse(KafkaProcessingStatus entity){

        KafkaProcessingStatusResponse dto=new KafkaProcessingStatusResponse();

        dto.setId(entity.getId());
        dto.setTopicName(entity.getTopicName());
        dto.setPartitionNumber(entity.getPartitionNumber());
        dto.setOffsetNumber(entity.getOffsetNumber());
        dto.setMessageKey(entity.getMessageKey());
        dto.setProcessingStatus(entity.getProcessingStatus());
        dto.setRetryCount(entity.getRetryCount());
        dto.setErrorMessage(entity.getErrorMessage());
        dto.setProcessedAt(entity.getProcessedAt());

        return dto;
    }
}
