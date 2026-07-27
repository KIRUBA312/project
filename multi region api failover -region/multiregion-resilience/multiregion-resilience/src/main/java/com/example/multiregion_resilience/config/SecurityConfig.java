package com.example.multiregion_resilience.config;


import com.example.multiregion_resilience.security.JwtAccessDeniedHandler;
import com.example.multiregion_resilience.security.JwtAuthenticationEntryPoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint
            jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler
            jwtAccessDeniedHandler;


    public SecurityConfig(
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {

        this.jwtAuthenticationEntryPoint =
                jwtAuthenticationEntryPoint;

        this.jwtAccessDeniedHandler =
                jwtAccessDeniedHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf ->
                        csrf.disable()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        )
                        .permitAll()
                        .requestMatchers(
                                "/actuator/health",
                                "/actuator/info"
                        )
                        .permitAll()
                        .requestMatchers(
                        		"/api/**",
                        		"/api/regions/**",
                        		"/api/health/**",
                        		"/api/failover/**",
                        		"/api/cache/**",
                        		"/api/audit-log/**")
                        .permitAll()
                        

                        .anyRequest()
                        .permitAll()
                )              
                .exceptionHandling(exception -> exception

                        .authenticationEntryPoint(
                                jwtAuthenticationEntryPoint
                        )

                        .accessDeniedHandler(
                                jwtAccessDeniedHandler
                        )
                );


        return http.build();
    }
}