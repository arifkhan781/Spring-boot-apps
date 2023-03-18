package com.work.club.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.club.dao.UserRepository;
import com.work.club.entity.User;
import com.work.club.exceptions.ResourceNotFoundException;
import com.work.club.payloads.UserDto;
import com.work.club.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
    UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<User> getAllUsers() {
	  List<User> users = this.userRepository.findAll();
	  return users;
	}

	@Override
	public User getUserById(Integer userId) {
		User foundUser = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));
		return foundUser;
	}

	@Override
	public User updateUser(User user, Integer userId) {
		User existingUser = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		if(user.getUserName()!=null) {
		existingUser.setUserName(user.getUserName());
		}
		if(user.getDesignation()!=null) {
		existingUser.setDesignation(user.getDesignation());
		}
		existingUser.setFollowing(user.isFollowing());
		
		if(user.getPassword()!=null) {
		existingUser.setPassword(user.getPassword());
		}
		if(user.getProfileImage()!=null) {
		existingUser.setProfileImage(user.getProfileImage());
		}
		if(user.getAbout()!=null) {
			existingUser.setAbout(user.getAbout());
		}
		userRepository.save(existingUser);
		return existingUser;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		String userName = userDto.getUserName();
		String password = userDto.getPassword();
		List<User>users = userRepository.findAll();
		boolean isExist = false;
		for(User user : users) {
			if(user.getUserName()!=null && user.getUserName().equals(userName)
			                 && user.getPassword()!=null &&  user.getPassword().equals(password)) {
			 	isExist= true;
			 	break;
			}
		}
		if(!isExist) {
		  User createdUser = this.modelMapper.map(userDto, User.class);
		  this.userRepository.save(createdUser);
		  return userDto;
		}
		return null;
	}

	@Override
	public void deleteUserById(Integer userId) {
	  
		User deleteUser = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(deleteUser);
	}

	@Override
	public List<User> searchUsers(String keyword) {
		List<User>users = this.userRepository.findAll();
		List<User> searchedUsers = users.stream().filter(user ->user.getUserName().contains(keyword)).collect(Collectors.toList());		
		return searchedUsers;
	}
	@Override
	public UserDto login(UserDto userDto) {
		
		List<User> users = this.userRepository.findAll();
		String userName = userDto.getUserName();
		String password = userDto.getPassword();
		UserDto newUser = new UserDto();
		for(User user : users) {
			if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
			 return newUser;
			}
		}
		return null;
	}

}
