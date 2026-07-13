package com.example.enterprise_iam.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserSession;
import com.example.enterprise_iam.repository.UserSessionRepository;
import com.example.enterprise_iam.service.UserSessionService;

@Service
public class UserSessionServiceImpl implements UserSessionService{

	@Autowired
	private UserSessionRepository userSessionRepository;

	@Override
	public void createSession(User user, 
			String jwtToken, String device, String ipAddress) {
		// TODO Auto-generated method stub
		
		UserSession session = new UserSession();
		
		session.setUser(user);
		session.setJwtToken(jwtToken);
		session.setDevice(device);
		session.setIpAddress(ipAddress);
		session.setLoginTime(LocalDateTime.now());
		session.setActive(true);
		
		userSessionRepository.save(session);
		
	}

	@Override
	public void logoutSession(String jwtToken) {
		// TODO Auto-generated method stub
		userSessionRepository.findByJwtToken(jwtToken).ifPresent(session ->{
			session.setLogoutTime(LocalDateTime.now());
			session.setActive(false);
			
			userSessionRepository.save(session);
		});
		
	}
	
	
	
}
