package com.blogapp.Services;

import java.util.List;

import com.blogapp.payloads.PostDto;

public interface PostService {

	//create post
	 PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	 
	 //get all Post
	 List<PostDto> getAllPost();
	 
	 //get post by categoryID
	 List<PostDto> getPostByCategory(Integer categoryId);
	 
	 //get post by userId
	 List<PostDto> getPostByUser(Integer userId);
	 
	 //update post 
	 PostDto updatePost(PostDto postDto, Integer postId);
	 
	 //delete post
	 void deletePost(Integer postId);
	 
	 List<PostDto> searchPosts(String keywords);
	 
}
