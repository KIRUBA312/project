package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.AuditLogResponse;
import com.example.cdc_synchronization_engine.entity.AuditLog;

@Component
public class AuditLogMapper {

    public AuditLogResponse toResponse(AuditLog entity){

        AuditLogResponse dto=new AuditLogResponse();

        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setAction(entity.getAction());
        dto.setEntityName(entity.getEntityName());
        dto.setEntityId(entity.getEntityId());
        dto.setCorrelationId(entity.getCorrelationId());
        dto.setIpAddress(entity.getIpAddress());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }
}
