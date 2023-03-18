package com.work.club.payloads;

import java.time.LocalTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse {
   
	private String message;
	private boolean isSuccess;
	private LocalTime currentTime;
	
	public ApiResponse(String message, boolean isSuccess, LocalTime currentTime) {
		super();
		this.message = message;
		this.isSuccess = isSuccess;
		this.currentTime = currentTime;
	}

	public LocalTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(LocalTime currentTime) {
		this.currentTime = currentTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	
	
	
	
}
