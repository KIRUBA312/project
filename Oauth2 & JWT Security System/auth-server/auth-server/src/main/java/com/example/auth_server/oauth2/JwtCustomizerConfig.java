package com.example.auth_server.oauth2;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

@Configuration
public class JwtCustomizerConfig {

	@Bean
	OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer(){
		
		return context ->{
			context.getClaims().claim("tenant_id", "TENANT001");
			context.getClaims().claim("roles", List.of("ADMIN"));
			context.getClaims().claim("permissions", List.of(
					"USER_READ","USER_CREATE"));
		};
	}
	
}
