package com.example.multiregion_resilience.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multiregion_resilience.entity.IdempotencyRecord;
import com.example.multiregion_resilience.enums.IdempotencyStatus;

@Repository
public interface IdempotencyRecordRepository extends 
JpaRepository<IdempotencyRecord, Long>{

	Optional<IdempotencyRecord> findByIdempotencyKey(
            String idempotencyKey
    );

    boolean existsByIdempotencyKey(
            String idempotencyKey
    );

    void deleteByExpiresAtBefore(
            LocalDateTime currentTime
    );

    long deleteByStatusAndExpiresAtBefore(
            IdempotencyStatus status,
            LocalDateTime currentTime
    );
	
}
