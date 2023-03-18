package com.work.club.exceptions;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.work.club.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>resourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false, LocalTime.now());
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
}
