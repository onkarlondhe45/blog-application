package com.blogapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Blog Application API")
				.description("This blogging application enables user to write blogs or articles and another user can also comment on this blogs.").version("1.0.0")
				.termsOfService("Terms of service URL")
				.contact(new Contact().name("Onkar Londhe").email("onkarlondhe@gmail.com").url("onkarlondhe@gmail.com"))
				.license(new License().name("Onkar Londhe").url("onkar.londhe")));
	}
}
