package com.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.Services.CategoryService;
import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.CategoryDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/createCategory")
	public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		categoryService.createCategory(categoryDto);
		return new ResponseEntity<String>("Category Created successfully...!", HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted...!",true), HttpStatus.OK);
	}
	
	@GetMapping("/get/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
		CategoryDto categoryById = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryById, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> allCategories = categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(allCategories, HttpStatus.OK);
	}
	
}
