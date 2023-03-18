package com.work.club.payloads;

public class ExpertiseDto {

	private String skillTitle;
	private String skillDescription;
	private Integer rating;
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
	
}
