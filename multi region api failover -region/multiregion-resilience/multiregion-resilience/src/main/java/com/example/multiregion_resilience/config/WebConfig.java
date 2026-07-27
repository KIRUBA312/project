package com.example.multiregion_resilience.config;

import com.example.multiregion_resilience.util.RequestIdUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class WebConfig extends OncePerRequestFilter {

    private static final String REQUEST_ID = "X-Request-Id";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        String requestId =
                request.getHeader(REQUEST_ID);

        if (requestId == null ||  requestId.isBlank()) {
            requestId = RequestIdUtil.generateRequestId();
        }
        response.setHeader(REQUEST_ID,requestId);

        try {
            RequestIdUtil.setRequestId(requestId);
            filterChain.doFilter( request, response);
        } finally {
            RequestIdUtil.clearRequestId();
        }
    }
}