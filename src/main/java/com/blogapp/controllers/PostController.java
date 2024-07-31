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

import com.blogapp.Services.PostService;
import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.PostDto;

@RestController
@RequestMapping("/apis")
public class PostController {

	@Autowired
	PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/createPost")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDto createPost = postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllPost")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> allPost = postService.getAllPost();		
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
	}
	
	@GetMapping("/getPostsByCategory/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> postByCategory = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);	
	}
	
	@GetMapping("/getPostsByUser/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> postByUser = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
	}
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto updatePost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	@DeleteMapping("deletePost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully...!", true), HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keywords){
		List<PostDto> searchPosts = postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
	}
}
