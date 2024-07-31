package com.blogapp.payloads;

import java.sql.Date;

import lombok.Data;

@Data
public class PostDto {

	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;

	private UserDto user;
	
	private CategoryDto category;
	

	
}
