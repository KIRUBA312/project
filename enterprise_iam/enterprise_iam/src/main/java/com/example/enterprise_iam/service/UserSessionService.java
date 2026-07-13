package com.example.enterprise_iam.service;

import com.example.enterprise_iam.entity.User;

public interface UserSessionService {

	void createSession(User user,String jwtToken,String device,
			String ipAddress);
	
	void logoutSession(String jwtToken);
	
}
