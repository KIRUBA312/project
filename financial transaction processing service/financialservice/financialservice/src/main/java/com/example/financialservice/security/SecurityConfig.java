package com.example.financialservice.security;

import java.net.PasswordAuthentication;

import org.springdoc.core.properties.SwaggerUiConfigProperties.Csrf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.annotation.security.PermitAll;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration config)
	throws Exception{
		
		return config.getAuthenticationManager();
		
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http)throws Exception {
		
		http
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session ->
		session.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(
				"/api/auth/**",
				"/swagger-ui/**",
				"/v3/api-docs/**",
				"/swagger-ui.html")
		.permitAll()
		.anyRequest()
		.authenticated())
		
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
	return http.build();	


	}
	
}
