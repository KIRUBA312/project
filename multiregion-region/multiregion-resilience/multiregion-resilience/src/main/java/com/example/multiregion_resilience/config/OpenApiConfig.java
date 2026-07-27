package com.example.multiregion_resilience.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(
                    new Info()
                        .title("Multi-Region API Failover & Resilience API")
                        .version("v1")
                        .description(
                            "Production-ready API for multi-region "
                            + "failover, health monitoring, resilience, "
                            + "cache synchronization and audit logging."
                        )
                )
                .components(
                    new Components()
                        .addSecuritySchemes(
                            "bearerAuth",
                            new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }
}