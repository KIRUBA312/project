package com.example.auth_server.service;

public interface AuditService {

	void logEvent(String username, 
			Long tenantid, 
			String eventType, String ipAddress);

}
