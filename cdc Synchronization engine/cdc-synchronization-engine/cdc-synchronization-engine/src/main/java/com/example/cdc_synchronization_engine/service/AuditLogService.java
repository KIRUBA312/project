package com.example.cdc_synchronization_engine.service;

import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;

public interface AuditLogService {
    void saveAudit(CDCEvent event);
}
