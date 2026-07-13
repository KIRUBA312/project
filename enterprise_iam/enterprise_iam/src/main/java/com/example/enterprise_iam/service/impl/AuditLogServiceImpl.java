package com.example.enterprise_iam.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.AuditLogResponseDto;
import com.example.enterprise_iam.entity.AuditLog;
import com.example.enterprise_iam.repository.AuditLogRepository;
import com.example.enterprise_iam.service.AuditLogService;
import com.example.enterprise_iam.util.MapperUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuditLogServiceImpl implements AuditLogService{
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	@Autowired
	private MapperUtil mapperUtil;
	@Override
	public List<AuditLogResponseDto> getAllLogs() {
		// TODO Auto-generated method stub
		return auditLogRepository.findAll().stream()
				.map(mapperUtil::toAuditLogResponse)
				.collect(Collectors.toList());
	}
	@Override
	public List<AuditLogResponseDto> getLogsByUser(Long userId) {
		// TODO Auto-generated method stub
		return auditLogRepository.findByPerformedBy(userId)
				.stream().map(mapperUtil::toAuditLogResponse)
				.collect(Collectors.toList());
	}
	@Override
	public List<AuditLogResponseDto> getLogsByAction(String action) {
		// TODO Auto-generated method stub
		return auditLogRepository.findByAction(action).stream()
				.map(mapperUtil::toAuditLogResponse)
				.collect(Collectors.toList());
	}
	@Override
	public List<AuditLogResponseDto> getLogsByDate(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return auditLogRepository.findByPerformedAtBetween(from.atStartOfDay(),
				to.atTime(23,59,59)).stream()
				.map(mapperUtil::toAuditLogResponse)
				.collect(Collectors.toList());
	}
	@Override
	public ApiResponseDto deleteOldLogs() {
		// TODO Auto-generated method stub
		LocalDateTime cutoff = LocalDateTime.now().minusMonths(6);
		auditLogRepository.findByPerformedAtBetween(LocalDateTime.MIN, cutoff)
		.forEach(auditLogRepository::delete);
		
		return new ApiResponseDto(true,"Old logs deleted successfully");
	}
	@Override
	public void logAction(String action, String entityName, Long entityId, Long performedBy, String details) {
		// TODO Auto-generated method stub
		AuditLog log = new AuditLog();
		
		log.setAction(action);
		log.setEntityName(entityName);
		log.setEntityId(entityId);
		log.setPerformedBy(performedBy);
		log.setPerformedAt(LocalDateTime.now());
		log.setDetails(details);
		
		auditLogRepository.save(log);
		
	
	}

}
