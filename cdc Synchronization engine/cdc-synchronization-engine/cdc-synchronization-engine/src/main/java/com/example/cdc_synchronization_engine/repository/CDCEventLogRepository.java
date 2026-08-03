package com.example.cdc_synchronization_engine.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.CDCEventLog;

@Repository
public interface CDCEventLogRepository
        extends JpaRepository<CDCEventLog, UUID> {
}