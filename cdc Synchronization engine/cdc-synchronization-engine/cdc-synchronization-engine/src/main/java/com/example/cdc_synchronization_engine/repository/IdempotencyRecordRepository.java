package com.example.cdc_synchronization_engine.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.IdempotencyRecord;

@Repository
public interface IdempotencyRecordRepository
        extends JpaRepository<IdempotencyRecord, Long> {

    Optional<IdempotencyRecord>
    findByEventId(String eventId);

    boolean existsByEventId(String eventId);
}