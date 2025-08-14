package com.senai.flora.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI floraOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Flora API")
                        .description("Flowers management")
                        .version("1.0")
                        .contact(new Contact()
                            .email("support@flora.com")
                            .name("Flora Team")
                        )
                );
    }
}
