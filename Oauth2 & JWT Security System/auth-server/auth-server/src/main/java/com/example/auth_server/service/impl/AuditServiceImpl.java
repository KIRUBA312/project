package com.example.auth_server.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth_server.entity.AuditLog;
import com.example.auth_server.repository.AuditLogRepository;
import com.example.auth_server.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService{
	
	@Autowired
	private AuditLogRepository auditLogRepository;

	@Override
	public void logEvent(String username, Long tenantid, String eventType, String ipAddress) {
		// TODO Auto-generated method stub
		AuditLog audit = new AuditLog();
		audit.setUsernaame(username);
		audit.setTenantId(tenantid);
		audit.setEventType(eventType);
		audit.setIpAddress(ipAddress);
		audit.setCreatedAt(LocalDateTime.now());
		
		auditLogRepository.save(audit);
	}
	
	

}
