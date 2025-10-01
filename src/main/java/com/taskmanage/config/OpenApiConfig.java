package com.taskmanage.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Task Manager API",
        version = "1.0",
        description = "API documentation for managing tasks (CRUD operations).",
        contact = @Contact(
            name = "Your Name",
            email = "youremail@example.com"
        )
    )
)
public class OpenApiConfig {
}
