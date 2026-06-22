package com.example.auth_server.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tenant = request.getHeader("X-Tenant-ID");
		request.setAttribute("tenantId", tenant);
		filterChain.doFilter(request, response);
		
	}

}
