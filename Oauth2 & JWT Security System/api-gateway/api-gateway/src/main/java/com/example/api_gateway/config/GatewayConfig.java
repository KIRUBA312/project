package com.example.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	RouteLocator customRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth-service", r-> r.path("/auth/**")
						.uri("http://localhost:8081"))
				.route("user-service", r-> r.path("/users/**")
						.uri("http://localhost:8082"))
				.route("resource-service",r-> r.path("/resource/**")
						.uri("http://localhost:8083"))
				.build();
	}
	
}
