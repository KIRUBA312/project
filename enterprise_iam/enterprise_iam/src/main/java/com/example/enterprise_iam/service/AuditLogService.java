package com.example.enterprise_iam.service;

import java.time.LocalDate;
import java.util.List;

import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.AuditLogResponseDto;

public interface AuditLogService {
	
	void logAction(String action,String entityName,Long entityId,
			Long performedBy,String details);

	List<AuditLogResponseDto> getAllLogs();

	List<AuditLogResponseDto> getLogsByUser(Long userId);

	List<AuditLogResponseDto> getLogsByAction(String action);

	List<AuditLogResponseDto> getLogsByDate(LocalDate from, LocalDate to);

	ApiResponseDto deleteOldLogs();

}
