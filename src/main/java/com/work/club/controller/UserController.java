package com.work.club.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.work.club.entity.User;
import com.work.club.payloads.ApiResponse;
import com.work.club.payloads.UserDto;
import com.work.club.service.FileService;
import com.work.club.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
   UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	FileService fileService;
	
	@Value("{project.images}")
	private String path;

	
	//getAllUsers
	@GetMapping("")
	public List<User>getAllUsers(){
		List<User> users = this.userService.getAllUsers();
		return users;
	}
	
	//getUserById;
	@GetMapping("/{userId}")
	public ResponseEntity<?>getUserById(@PathVariable("userId") Integer userId){
		User foundUser = this.userService.getUserById(userId);
		return new ResponseEntity<User>(foundUser, HttpStatus.OK);
		
		
	}
	//updateUser
	@PutMapping("/update/{userId}")
	public ResponseEntity<?>updateUser(@RequestBody User user, @PathVariable("userId") Integer userId){
		User updateUser = this.userService.updateUser(user, userId);
		if(updateUser.equals(user)) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("Updated User can not be Same as existing one !!", false, LocalTime.now()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(updateUser, HttpStatus.OK);
	}
	
	//createUserOrSignUp;
	@PostMapping("/signUp")
	public ResponseEntity<?>signUp(@RequestBody UserDto userDto){
		UserDto createdUser = this.userService.createUser(userDto);
		if(createdUser == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("User already Exist..PLease Sign in !!", false, LocalTime.now()),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("Sign up Successfull !!", true, LocalTime.now()), HttpStatus.CREATED);
	}
	
	//deleteUser
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?>deleteUser(@PathVariable("userId") Integer userId){
		this.userService.deleteUserById(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully !!", true, LocalTime.now()),HttpStatus.OK);
	}
	
	//searchUser(on the basis of the userName)
	@GetMapping("/search")
	public ResponseEntity<?>searchUser(@RequestParam("keyword") String keyword){
		List<User> foundUsers = this.userService.searchUsers(keyword);
		if(foundUsers.isEmpty()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("No Users found for this search !!", false, LocalTime.now()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(foundUsers, HttpStatus.OK);
	}
	
	//login functionality;
	@GetMapping("/login")
	public ResponseEntity<?>login(@RequestBody UserDto userDto){
		
		UserDto validateUser = this.userService.login(userDto);
		if(validateUser==null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("User Not Exists !!..Please SignUp", false, LocalTime.now()),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Login Successfully !!", true,LocalTime.now()),HttpStatus.OK);
		
	}
	//seperate endPoint for the image uploading and downloading feature;
	@PostMapping("/users/{userId}/image/upload")
	public ResponseEntity<ApiResponse>uploadProfileImage(@PathVariable("userId") Integer userId, @RequestParam("image") MultipartFile image) throws IOException{
		User user = this.userService.getUserById(userId);
		String fileName = this.fileService.uploadImage(path, image);
		user.setProfileImage(fileName);
		User updatedUser = this.userService.updateUser(user, userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Profile Image updated Successfully !!", true, LocalTime.now()), HttpStatus.OK);
	}
	
	
	
	
}
