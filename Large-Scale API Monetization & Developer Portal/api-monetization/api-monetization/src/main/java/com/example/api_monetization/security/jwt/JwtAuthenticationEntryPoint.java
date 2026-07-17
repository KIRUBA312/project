package com.example.api_monetization.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.api_monetization.dto.common.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException authException) 
					throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		ErrorResponse error = ErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.error("Unauthorized")
				.message(authException.getMessage())
				.path(request.getRequestURI()).build();
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.findAndRegisterModules();
		
		mapper.writeValue(response.getOutputStream(), error);
		
		
	}
	
	

}
