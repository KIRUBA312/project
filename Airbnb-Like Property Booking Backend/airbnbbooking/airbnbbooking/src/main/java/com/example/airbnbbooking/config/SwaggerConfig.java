package com.example.airbnbbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI().info(new Info()
				.title("Airbnb Property Booking API")
				.version("1.0")
				.description("Airbnb Property Booking Backend System")
				.contact(new Contact()
						.name("Development Team")
						.email("admin@airbnbbooking.com")));
	}
	
}
