package com.example.stocktradingengine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(
						 new Info()
						 .title("Stock Trading Engine API")
						 .description("Stock Trading Engine Api documentation")
						 .version("1.0")
						 .license(new License()
								 .name("Open Source License"))
						);
	}
	
	
}
