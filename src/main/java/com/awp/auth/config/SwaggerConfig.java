package com.awp.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        // 1. Sets your project name, version 1.0, and description
        info = @Info(
                title = "Aura Wellness Platform API",
                version = "1.0",
                description = "Backend REST API Documentation for the Aura Security and Identity Management layer."
        ),
        // 2. Applies the security requirement globally to all endpoints
        security = @SecurityRequirement(name = "Bearer Authentication")
)
// 3. Defines the actual security protocol configuration layout
@SecurityScheme(
        // Must match the security requirement name exactly
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "JWT Bearer Token"
)
public class SwaggerConfig {

    /*
    @Bean
    public OpenAPI customOpenAPI(){
        SecurityScheme securityScheme= new SecurityScheme();

        securityScheme.type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT Bearer Token");

        SecurityRequirement securityRequirement= new SecurityRequirement();

        securityRequirement.addList("Bearer Authentication");

        return new OpenAPI().components( new Components().addSecuritySchemes("Bearer Authentication",securityScheme))
                .addSecurityItem(securityRequirement);
    }
     */
}
