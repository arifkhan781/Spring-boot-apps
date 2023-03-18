package com.work.club.service;

import com.work.club.payloads.ExpertiseDto;

public interface ExpertiseService {

	//addExpertise;
	ExpertiseDto addExpertise(ExpertiseDto expertiseDto, Integer userId);
	//updateExpertise
	ExpertiseDto updateExpertise(ExpertiseDto expertiseDto, Integer userId, Integer expertiseId);
	//delete expertise
	public boolean deleteExpertise(Integer userId, Integer expertiseId);
}
