package com.example.bankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingsystem.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{

}
