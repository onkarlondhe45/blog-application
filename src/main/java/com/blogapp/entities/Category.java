package com.blogapp.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	private String categoryTitle;
	private String categoryDescription;
	
	// cascade - performing operation like remove , if parent remove child automatic remove. and 
	//if parent add child dont need to add again it will add automatically.
	
	// fetchtype.lazy - if we want to remove parent but child wont be remove then we use fetch.
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Post> posts = new ArrayList<>();
	
}
