package com.burea.markdowneditor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

/**
 * Ricardo Carrasco S
 * 06-11-2020
 **/
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerConfig(){
        return new OpenAPI()
                .info(new Info()
                .title("Markdown Editor")
                .version("1.0")
                .description("Editor de texto en tiempo real"));
    }
}
