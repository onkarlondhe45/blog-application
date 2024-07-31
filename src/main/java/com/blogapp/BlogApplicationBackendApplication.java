package com.blogapp;

import org.modelmapper.ModelMapper;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplicationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationBackendApplication.class, args);
		System.out.println("Application Started...!");
		System.out.println("Application running on port number : 8081");
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/**")
            .build();
    }
	
}
