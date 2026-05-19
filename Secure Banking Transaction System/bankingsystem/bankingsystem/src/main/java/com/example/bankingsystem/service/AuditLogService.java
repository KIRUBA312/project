package com.example.bankingsystem.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.bankingsystem.dto.AuditLogResponseDto;

public interface AuditLogService {

	List<AuditLogResponseDto> getAllLogs();

}
