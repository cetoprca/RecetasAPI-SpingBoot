package com.github.cetoprca.recetasapispringboot.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TFG Recetas")
                        .version("0.1.0")
                        .description("API de red social recetas"))
                .components(new Components()
                        .addSecuritySchemes("JSESSIONID", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.COOKIE)
                                .name("JSESSIONID")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("JSESSIONID"));
    }
}
