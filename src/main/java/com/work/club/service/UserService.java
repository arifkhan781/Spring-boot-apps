package com.work.club.service;

import java.util.List;

import com.work.club.entity.User;
import com.work.club.payloads.UserDto;

public interface UserService {
 
	//get all users;
	public List<User>getAllUsers();
	//getuserbyId;
	public User getUserById(Integer userId);
	//updateUserbyId;
	public User updateUser(User user, Integer userId);
	//createUser
	public UserDto createUser(UserDto userDto);
	//deleteUserById;
	public void deleteUserById(Integer userId);
	//searchUserByName;
	public List<User>searchUsers(String keyword);
	//loginByUserNameAndPassword;
	public UserDto login(UserDto userDto);
}
