package com.example.enterprise_iam.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{

	List<AuditLog> findByPerformedBy(Long performedBy);
	List<AuditLog> findByAction(String action);
	List<AuditLog> findByPerformedAtBetween(LocalDateTime from,
			LocalDateTime to);
}
