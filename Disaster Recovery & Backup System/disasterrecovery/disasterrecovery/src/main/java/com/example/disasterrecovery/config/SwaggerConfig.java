package com.example.disasterrecovery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI disasterRecoveryOpenApi() {
		return new OpenAPI().info(new Info()
				.title("Disaster Recovery & Backup System API")
				.version("1.0")
				.description("Enterprise Backup & Restore Management APIs")
				.contact(new Contact()
						.name("Admin Team")
						.email("admin@backup.com")));
	}
}
