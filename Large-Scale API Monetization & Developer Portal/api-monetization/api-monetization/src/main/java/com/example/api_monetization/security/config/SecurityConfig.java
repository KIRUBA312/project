package com.example.api_monetization.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.example.api_monetization.security.jwt.JwtAuthenticationEntryPoint;
import com.example.api_monetization.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
	throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.cors(Customizer.withDefaults())
			.sessionManagement(session ->
					session.sessionCreationPolicy(
							SessionCreationPolicy.STATELESS))
			.exceptionHandling(exception->exception
					.authenticationEntryPoint(jwtAuthenticationEntryPoint))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
							"/api/auth/**",
							"/api/admin/**",
							"/api/users/**",
							"/api/subscriptions/**",
							"/api/developers/**",
							"/api/notifications/**",
							"/api/**","/api/analytics/**",
							"/api/billing/**",
							"/swagger-ui/**",
							"/v3/api-docs/**",
							"/api-docs/**",
							"/actuator/**")
					.permitAll()
					.anyRequest()
					.authenticated());
		return http.build();
			
	}
	
	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration)
	throws Exception{
		return configuration.getAuthenticationManager();
	}
}
