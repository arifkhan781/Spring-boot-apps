package com.work.club.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String password;
	private String designation;
	private String about;
	private String profileImage;
	private boolean  isFollowing;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Post>posts= new ArrayList<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Expertise>expertise = new ArrayList<>();
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public boolean isFollowing() {
		return isFollowing;
	}
	public void setFollowing(boolean isFollowing) {
		this.isFollowing = isFollowing;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public List<Expertise> getExpertise() {
		return expertise;
	}
	public void setExpertise(List<Expertise> expertise) {
		this.expertise = expertise;
	}
	
}
