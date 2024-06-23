package com.spring_mongo_docker_git.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("spring-mongo-docker-git")
                        .version("1.0")
                        .description("""
                                Spring-Boot application with mongodb database hosted in cloud
                                                                
                                planning to add git actions to the project and
                                generate a docker image and publish it to docker repo. 
                                """));
//                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
//                .components(new io.swagger.v3.oas.models.Components()
//                        .addSecuritySchemes("Bearer Authentication",
//                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")));
    }
}

