package com.example.mariiatrofimovatask6.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Counter Api",
                description = "Microservice for managing counters.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Mariia Trofimova",
                        url = "https://github.com/MariiaTrofimova/mariia-trofimova-task-6"
                )
        )
)
public class OpenApiConfig {

}
