package com.example.financialservice.service;

import java.util.List;


import com.example.financialservice.dto.AuditResponseDto;

public interface AuditService {

	List<AuditResponseDto> getAllAuditLogs();

	List<AuditResponseDto> getAuditLogsByTransactionId(
			String transactionId);

}
