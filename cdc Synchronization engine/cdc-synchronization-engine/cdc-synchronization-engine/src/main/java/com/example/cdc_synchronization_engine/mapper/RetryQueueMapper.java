package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.RetryQueueResponse;
import com.example.cdc_synchronization_engine.entity.RetryQueue;

@Component
public class RetryQueueMapper {

    public RetryQueueResponse toResponse(RetryQueue entity){

        RetryQueueResponse dto=new RetryQueueResponse();

        dto.setId(entity.getId());
        dto.setTopicName(entity.getTopicName());
        dto.setPayload(entity.getPayload());
        dto.setRetryAttempt(entity.getRetryAttempt());
        dto.setNextRetryTime(entity.getNextRetryTime());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}