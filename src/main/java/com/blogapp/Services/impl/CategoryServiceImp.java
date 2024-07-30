package com.blogapp.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.Services.CategoryService;
import com.blogapp.entities.Category;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.repositories.CategoryRepo;

@Service
public class CategoryServiceImp implements CategoryService {

	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = categoryRepo.save(category);
		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCat = categoryRepo.save(cat);
		return modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		categoryRepo.delete(cat);	
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		return modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat)->modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		
		return catDtos;
	}

	 

}
