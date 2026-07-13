package com.example.enterprise_iam.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	public String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		
		if(authentication == null) {
			return null;
		}
		return authentication.getName();
	}
	
	public boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		return authentication !=null && authentication.isAuthenticated()
				&& !"anonymousUser".equals(authentication.getName());
	}
	
}
