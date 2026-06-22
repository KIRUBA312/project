package com.example.auth_server.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthorizationServerConfig {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain authorizationSecurityFilterChain(
			HttpSecurity http)throws Exception{
		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
				new OAuth2AuthorizationServerConfigurer();
		http.securityMatcher(authorizationServerConfigurer
				.getEndpointsMatcher());
		http.with(authorizationServerConfigurer, Customizer.withDefaults());
		http.authorizeHttpRequests(authorize -> authorize.anyRequest()
				.authenticated());
		http.formLogin(Customizer.withDefaults());
		return http.build();
	}
}
