package com.formation.fomation.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Gestion des Formation Pro")
                        .description("Documentation de l'API pour le projet de gestion des Formation Pro")
                        .version("1.0"));
    }
}
