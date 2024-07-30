package com.blogapp.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.Services.UserService;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.UserDto;
import com.blogapp.repositories.UserRepo;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = dtoTOUser(userDto);
		User savedUser = userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = userRepo.save(user);
		return userToDto(updateUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> users = userRepo.findAll();
		
		List<UserDto> allUsers = users.stream().map(user-> userToDto(user)).collect(Collectors.toList());
		return allUsers;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		
		userRepo.delete(user);
	}

	
	public User dtoTOUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);

		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

}
