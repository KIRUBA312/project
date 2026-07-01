package com.example.coupon_discount_engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Coupon Discount Engine API")
						.version("1.0")
						.description("Enterprise Coupon Discount Engine using Spring Boot, JPA, Strategy Pattern, Idempotency and Transaction Management.")
						.contact(new Contact()
								.name("kirubakaran")
								.email("admin@example.com")
								)
						.license(new License()
								.name("Apache 2.0")))
				.externalDocs(new ExternalDocumentation()
						.description("Project Documentation")
						);
				
	}
	
}
