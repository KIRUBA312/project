package com.example.auth_server.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator{

	@Override
	public boolean hasPermission(
			Authentication authentication, 
			Object targetDomainObject, 
			Object permission) {
		// TODO Auto-generated method stub
		return authentication.getAuthorities().stream()
				.anyMatch(auth -> auth.getAuthority()
						.equals(permission));
		
	}

	@Override
	public boolean hasPermission(
			Authentication authentication, 
			Serializable targetId, 
			String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
