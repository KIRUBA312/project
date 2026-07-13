package com.example.enterprise_iam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JwtAuthenticationProvider {

	private final CustomUserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	public JwtAuthenticationProvider(CustomUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = 
				new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		
		return provider;
	}
	
}
