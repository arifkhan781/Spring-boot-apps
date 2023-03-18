package com.work.club.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.work.club.payloads.ApiResponse;
import com.work.club.payloads.ExpertiseDto;
import com.work.club.service.ExpertiseService;

@RestController
public class ExpertiseController {

	@Autowired
	ExpertiseService expertiseService;
	
	//add expertise of a user;
	@PostMapping("/users/{userId}/addExpertise")
	public ResponseEntity<?>addExpertise(@RequestBody ExpertiseDto expertiseDto, @PathVariable("userId") Integer userId){
		this.expertiseService.addExpertise(expertiseDto, userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Expertise Details Added Successfully !!", true, LocalTime.now()),HttpStatus.OK);
  }
	//deleteExpertise;
	@DeleteMapping("/users/{userId}/deleteExpertise/{expertiseId}")
	public ResponseEntity<?>deleteExpertise(@PathVariable("userId") Integer userId, @PathVariable("expertiseId") Integer expertiseId){
	this.expertiseService.deleteExpertise(userId, expertiseId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("Expertise Details deleted Successfully !!", true, LocalTime.now()), HttpStatus.OK);
	}
	
	//updateExpertise 
	@PutMapping("/users/{userId}/updateExpertise/{expertiseId}")
	public ResponseEntity<?>updateExpertise( @RequestBody ExpertiseDto expertiseDto,@PathVariable("userId") Integer userId, @PathVariable("expertiseId") Integer expertiseId){
	 ExpertiseDto updatedExpertise = this.expertiseService.updateExpertise(expertiseDto, userId, expertiseId);
	 return new ResponseEntity<ApiResponse>(new ApiResponse("Expertise Details Updated Successfully!!", true, LocalTime.now()), HttpStatus.OK);
	}
}