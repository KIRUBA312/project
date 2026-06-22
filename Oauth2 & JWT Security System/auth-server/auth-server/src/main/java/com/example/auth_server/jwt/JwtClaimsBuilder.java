package com.example.auth_server.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JwtClaimsBuilder {

	public Map<String, Object> buildClaims(Long tenantId,String role){
		Map<String, Object> claims = new HashMap<>();
		claims.put("tenant_id", tenantId);
		claims.put("role", role);
		
		return claims;
	}
}
