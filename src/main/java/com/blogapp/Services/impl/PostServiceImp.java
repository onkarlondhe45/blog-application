package com.blogapp.Services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.Services.PostService;
import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.PostDto;
import com.blogapp.repositories.CategoryRepo;
import com.blogapp.repositories.PostRepo;
import com.blogapp.repositories.UserRepo;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		Post post = modelMapper.map(postDto, Post.class);

		post.setImageName("Default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post newPost = postRepo.save(post);

		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPost() {

		List<Post> posts = postRepo.findAll();
		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> findPostsByCategory = postRepo.findByCategory(category);
		List<PostDto> allPost = findPostsByCategory.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return allPost;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));
		List<Post> findByUser = postRepo.findByUser(user);
		List<PostDto> collect = findByUser.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post save = postRepo.save(post);
		return modelMapper.map(save, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> searchPosts(String keywords) {

		List<Post> posts = postRepo.searchByTitle("%"+ keywords +"%");
		System.out.println(posts);
		List<PostDto> collect = posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

}
