package com.example.financialservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financialservice.dto.AuditResponseDto;
import com.example.financialservice.service.AuditService;

@RestController
@RequestMapping("/api/audits")
public class AuditController {
	
	@Autowired
	private AuditService auditService;
	
	@GetMapping
	public ResponseEntity<List<AuditResponseDto>> getAllAuditLogs(){
		return ResponseEntity.ok(auditService.getAllAuditLogs());
	}
	
	@GetMapping("/{transactionId}")
	public ResponseEntity<List<AuditResponseDto>> getAuditLogsByTransactionId(
			@PathVariable String transactionId){
		
		return ResponseEntity.ok(auditService
				.getAuditLogsByTransactionId(transactionId));
		
	}

}
