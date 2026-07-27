package com.example.multiregion_resilience.service;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import com.example.multiregion_resilience.dto.AuditLogResponse;
import com.example.multiregion_resilience.dto.PageResponse;

public interface AuditService {

	PageResponse<AuditLogResponse> getAuditLogs(
			int page, 
			int size, 
			String requestId, 
			String userId, 
			String action,
			String region, 
			String status);

	Page<AuditLogResponse> getAuditLogsByRequestId(
			String requestId, int page, int size);

	Page<AuditLogResponse> getAuditLogsByUserId(
			String userId, int page, int size);

	Page<AuditLogResponse> getAuditLogsByAction(
			String action, int page, int size);

	Page<AuditLogResponse> getAuditLogsByRegion(
			String region, int page, int size);

	Page<AuditLogResponse> getAuditLogsByStatus(
			String status, int page, int size);

}
