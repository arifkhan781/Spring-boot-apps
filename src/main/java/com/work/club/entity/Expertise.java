package com.work.club.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Expertise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer exp_id;
	private String skillTitle;
	private String skillDescription;
	private Integer rating;
	
	@ManyToOne
	@JsonBackReference
	private User user;
	public Integer getExp_id() {
		return exp_id;
	}
	public void setExp_id(Integer exp_id) {
		this.exp_id = exp_id;
	}
	public String getSkillTitle() {
		return skillTitle;
	}
	public void setSkillTitle(String skillTitle) {
		this.skillTitle = skillTitle;
	}
	public String getSkillDescription() {
		return skillDescription;
	}
	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
