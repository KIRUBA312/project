package com.example.cdc_synchronization_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.AuditLog;

@Repository
public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {

    List<AuditLog>
    findByUsername(String username);

    List<AuditLog>
    findByEntityName(String entityName);
}