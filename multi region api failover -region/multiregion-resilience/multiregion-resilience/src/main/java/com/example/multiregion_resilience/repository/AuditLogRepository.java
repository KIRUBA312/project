package com.example.multiregion_resilience.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multiregion_resilience.entity.AuditLog;

@Repository
public interface AuditLogRepository extends 
JpaRepository<AuditLog, Long>{

	Page<AuditLog> findByRequestId(
            String requestId,
            Pageable pageable
    );

    Page<AuditLog> findByUserId(
            String userId,
            Pageable pageable
    );

    Page<AuditLog> findByAction(
            String action,
            Pageable pageable
    );

    Page<AuditLog> findByRegion(
            String region,
            Pageable pageable
    );

    Page<AuditLog> findByStatus(
            String status,
            Pageable pageable
    );

    Page<AuditLog> findAllByOrderByCreatedAtDesc(
            Pageable pageable
    );
}
