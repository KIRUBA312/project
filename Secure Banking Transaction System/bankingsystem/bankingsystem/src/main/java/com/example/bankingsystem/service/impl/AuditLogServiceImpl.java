package com.example.bankingsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.dto.AuditLogResponseDto;
import com.example.bankingsystem.entity.AuditLog;
import com.example.bankingsystem.repository.AuditLogRepository;
import com.example.bankingsystem.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService{

	@Autowired
	private AuditLogRepository auditLogRepository;

	@Override
	public List<AuditLogResponseDto> getAllLogs() {
		// TODO Auto-generated method stub
		return auditLogRepository.findAll().stream()
				.map(this::maptoDto)
				.collect(Collectors.toList()); 
	}
	
	private AuditLogResponseDto maptoDto(
			AuditLog auditLog) {
		
		AuditLogResponseDto dto = new AuditLogResponseDto();
		dto.setId(auditLog.getId());
		dto.setAction(auditLog.getAction());
		dto.setPerformedBy(auditLog.getPerformedBy());
		dto.setPerformedAt(auditLog.getPerformedAt());
		
		return dto;
		
	}
	
	
	
	
}
