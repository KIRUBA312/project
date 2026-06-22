package com.example.auth_server.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;

@Configuration
public class OAuth2TokenGeneratorConfig {

	@Bean
	JwtGenerator jwtGenerator(JwtEncoder encoder) {
		return new JwtGenerator(encoder);
	}
	
}
