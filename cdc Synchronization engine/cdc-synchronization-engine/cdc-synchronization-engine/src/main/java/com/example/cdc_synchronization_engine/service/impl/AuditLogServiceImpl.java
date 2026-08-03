package com.example.cdc_synchronization_engine.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.entity.AuditLog;
import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.repository.AuditLogRepository;
import com.example.cdc_synchronization_engine.service.AuditLogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuditLogServiceImpl
        implements AuditLogService {

    private final AuditLogRepository repository;

    @Override
    public void saveAudit(CDCEvent event) {

        AuditLog audit = new AuditLog();

        
        audit.setUsername("SYSTEM");
        
        audit.setAction(event.getOperation());
       
        audit.setEntityName(event.getEntityName());

        audit.setEntityId(event.getEntityId());
        
        audit.setCorrelationId(event.getCorrelationId());

        audit.setIpAddress("127.0.0.1");

        audit.setCreatedAt(LocalDateTime.now());

        repository.save(audit);
    }

}