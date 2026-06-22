package com.example.auth_server.audit;

import org.springframework.stereotype.Component;

@Component
public class AuditEventListener {

	public void audit(
			String username,
			String action) {
		System.out.println(
				"AUDIT => "+ username +" : "+action);
	}
	
}
