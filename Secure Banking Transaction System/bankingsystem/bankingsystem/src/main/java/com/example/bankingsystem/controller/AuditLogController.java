package com.example.bankingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.dto.AuditLogResponseDto;
import com.example.bankingsystem.service.AuditLogService;

@RestController
@RequestMapping("/api/auditlogs")
public class AuditLogController {
	
	@Autowired
	private AuditLogService auditLogService;
	
	@GetMapping
	public ResponseEntity<List<AuditLogResponseDto>> getAllLogs(){
		return ResponseEntity.ok(auditLogService.getAllLogs());
	}

}
