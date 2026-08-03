package com.example.cdc_synchronization_engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {

        final String securitySchemeName = "BearerAuth";

        return new OpenAPI()

                .info(new Info()

                        .title("CDC Synchronization Engine API")

                        .description(
                                "Enterprise Change Data Capture Synchronization Engine")

                        .version("1.0")

                        .contact(new Contact()

                                .name("CDC Team")

                                .email("support@cdc.com"))

                        .license(new License()

                                .name("Apache 2.0")))

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName))

                .components(

                        new Components()

                                .addSecuritySchemes(

                                        securitySchemeName,

                                        new SecurityScheme()

                                                .name(securitySchemeName)

                                                .type(SecurityScheme.Type.HTTP)

                                                .scheme("bearer")

                                                .bearerFormat("JWT")

                                )

                );

    }

}