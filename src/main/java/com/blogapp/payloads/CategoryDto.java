package com.blogapp.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

	private int categoryId;
	
	@NotBlank
	@Size(min = 3, message = "Category title must be contains min 3 characters")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10, message = "Category Description must be contains 10 characters")
	private String categoryDescription;
	
}
 