package com.example.financialservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financialservice.dto.AuditResponseDto;
import com.example.financialservice.entity.TransactionAudit;
import com.example.financialservice.repository.TransactionAuditRepository;
import com.example.financialservice.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService{

	@Autowired
	private TransactionAuditRepository auditRepository;

	@Override
	public List<AuditResponseDto> getAllAuditLogs() {
		// TODO Auto-generated method stub
		return auditRepository.findAll().stream()
				.map(this::maptoDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<AuditResponseDto> getAuditLogsByTransactionId(String transactionId) {
		// TODO Auto-generated method stub
		return auditRepository.findByTransactionId(transactionId)
				.stream().map(this::maptoDto)
				.collect(Collectors.toList());
	}
	
	private AuditResponseDto maptoDto(TransactionAudit audit) {
		AuditResponseDto dto = new AuditResponseDto();
		dto.setAuditId(audit.getAuditId());
		dto.setTransactionId(audit.getTransactionId());
		dto.setEventType(audit.getEventType());
		dto.setMessage(audit.getMessage());
		dto.setCreatedAt(audit.getCreatedAt());
		
		return dto;
		
	}
	
	
	
}
