package com.blogapp.Services;

import java.util.List;

import com.blogapp.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	
	//get all categories
	List<CategoryDto> getAllCategories();

	//getByID
	CategoryDto getCategoryById(Integer categoryId);
	
	
}
