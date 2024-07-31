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

import com.blogapp.Services.UserService;
import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/user")
public class UserController {
	@Autowired
	UserService userService;

	// create user
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUser = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
	}

	// update user
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int userId) {

		UserDto updateUser = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
	}

	// delete user
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully.", true), HttpStatus.OK);
	}

	// get all user

	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
	}

	// get user by id
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId) {
		UserDto userById = userService.getUserById(userId);
		return new ResponseEntity<UserDto>(userById, HttpStatus.OK);
	}

}
