package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.CDCEventLogResponse;
import com.example.cdc_synchronization_engine.entity.CDCEventLog;

@Component
public class CDCEventLogMapper {

    public CDCEventLogResponse toResponse(CDCEventLog log){

        CDCEventLogResponse response=new CDCEventLogResponse();

        response.setId(log.getId());
        response.setTableName(log.getTableName());
        response.setOperation(log.getOperation());
        response.setEntityId(log.getEntityId());
        response.setPayload(log.getPayload());
        response.setCorrelationId(log.getCorrelationId());
        response.setCreatedAt(log.getCreatedAt());

        return response;
    }
}