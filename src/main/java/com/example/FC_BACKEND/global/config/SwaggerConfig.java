package com.example.FC_BACKEND.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "건국대학교 컴퓨터공학부 해커톤 4팀",
                description = "Springdoc을 이용한 Swagger API 문서입니다.",
                version = "1.0"
        )
)


@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                        .components(new Components());
        }
}
