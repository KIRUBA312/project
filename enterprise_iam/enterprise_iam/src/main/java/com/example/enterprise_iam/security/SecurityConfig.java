package com.example.enterprise_iam.security;

import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	private final OAuth2UserServiceImpl oAuth2UserServiceImpl;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
			AuthenticationProvider authenticationProvider,
			JwtAuthenticationEntryPoint authenticationEntryPoint,
			OAuth2UserServiceImpl oAuth2UserServiceImpl,
			OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler,
			OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationProvider = authenticationProvider;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.oAuth2UserServiceImpl = oAuth2UserServiceImpl;
		this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
		this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable())
		.cors(Customizer.withDefaults())
		.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.exceptionHandling(ex -> ex
				.authenticationEntryPoint(authenticationEntryPoint))
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(
						"/api/auth/**",
						"/swagger-ui/**",
						"/swagger-ui.html",
						"/api-docs/**",
						"/api-docs","/api/roles/**",
						"/v3/api-docs/**",
						"/oauth2/**",
						"/api/permissions/**",
						"/api/admin/**",
						"/api/audit/**",
						
						"/api/mfa/**")
				.permitAll()
//				.requestMatchers(""
//						)
//				.hasRole("ADMIN")
				
				.anyRequest()
				.authenticated())
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter, 
				UsernamePasswordAuthenticationFilter.class);
//		.oauth2Login(oauth -> oauth
//        .userInfoEndpoint(userInfo ->
//                userInfo.userService(oAuth2UserServiceImpl))
//        .successHandler(oAuth2LoginSuccessHandler)
//        .failureHandler(oAuth2AuthenticationFailureHandler)
//);
		
		return http.build();
		
	}
	
}
