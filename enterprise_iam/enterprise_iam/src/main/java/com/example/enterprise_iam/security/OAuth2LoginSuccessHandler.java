package com.example.enterprise_iam.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.enterprise_iam.util.JwtUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    public OAuth2LoginSuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        UserDetails email = user.getAttribute("email");

        String accessToken = jwtUtil.generateAccessToken(email);

        response.setContentType("application/json");

        response.getWriter().write("""
                {
                    "message":"OAuth2 Login Successful",
                    "accessToken":"%s"
                }
                """.formatted(accessToken));
    }
}