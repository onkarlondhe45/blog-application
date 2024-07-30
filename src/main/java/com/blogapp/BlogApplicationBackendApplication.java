package com.blogapp;

import org.modelmapper.ModelMapper;
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
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}
}
