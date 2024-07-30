package com.blogapp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=3, message = "name must be greater than 3 characters..!")
	private String name;
	@Email(message = "email address is not valid..!")
	private String email;
	@NotEmpty(message = "incorrect password..!")
	private String password;
	@NotEmpty
	private String about;
	
}
