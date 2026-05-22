package com.example.financialservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
						.title("Financial Transaction Processing Service API")
						.version("1.0")
						.description(
								"Secure Banking and Financial Transaction Processing System")
						.contact(new Contact()
								.name("kiruba")
								.email("admin@financial.com"))
						);

	}
	
}
