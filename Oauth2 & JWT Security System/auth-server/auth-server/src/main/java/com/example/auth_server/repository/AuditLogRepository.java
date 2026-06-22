package com.example.auth_server.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_server.entity.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{
	
	List<AuditLog> findByUsername(String username);
	List<AuditLog> findByEventType(String eventType);
	List<AuditLog> findByCreatedAtBetween(LocalDateTime start,
			LocalDateTime end);
	List<AuditLog> findByUsernameAndEventType(String username,
			String eventType);

}
