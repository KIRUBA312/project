package com.example.enterprise_iam.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.AuditLogResponseDto;
import com.example.enterprise_iam.service.AuditLogService;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

	@Autowired
	private AuditLogService auditLogService;
	
	@GetMapping
	public ResponseEntity<List<AuditLogResponseDto>> getAlllogs(){
		return ResponseEntity.ok(auditLogService.getAllLogs());
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<AuditLogResponseDto>> getLogsByUser(
			@PathVariable Long userId){
		return ResponseEntity.ok(auditLogService.getLogsByUser(userId));
	}
	@GetMapping("/action/{action}")
	public ResponseEntity<List<AuditLogResponseDto>> getLogsByAction(
			@PathVariable String action){
		return ResponseEntity.ok(auditLogService.getLogsByAction(action));
	}
	@GetMapping("/date")
	public ResponseEntity<List<AuditLogResponseDto>> getLogsByDate(
			@RequestParam
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			LocalDate from,
			
			@RequestParam
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			LocalDate to){
		return ResponseEntity.ok(auditLogService.getLogsByDate(from, to));
		
	}
	@DeleteMapping("/cleanup")
	public ResponseEntity<ApiResponseDto> deleteOldLogs(){
		return ResponseEntity.ok(auditLogService.deleteOldLogs());
	}
	
}
