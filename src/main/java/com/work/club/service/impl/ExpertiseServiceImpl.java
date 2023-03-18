package com.work.club.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.club.dao.ExpertiseRepository;
import com.work.club.entity.Expertise;
import com.work.club.entity.User;
import com.work.club.exceptions.ResourceNotFoundException;
import com.work.club.payloads.ExpertiseDto;
import com.work.club.service.ExpertiseService;
import com.work.club.service.UserService;
@Service
public class ExpertiseServiceImpl implements ExpertiseService {

	
	@Autowired
    ModelMapper modelMapper;
	
	@Autowired
	ExpertiseRepository expertiseRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public ExpertiseDto addExpertise(ExpertiseDto expertiseDto, Integer userId) {
		User user = this.userService.getUserById(userId);
		Expertise expertise = this.modelMapper.map(expertiseDto, Expertise.class);
		expertise.setUser(user);
		this.expertiseRepository.save(expertise);
		return expertiseDto;
	}

	@Override
	public ExpertiseDto updateExpertise(ExpertiseDto expertiseDto, Integer userId , Integer expertiseId) {
		User user = this.userService.getUserById(userId);
		Expertise expertise = this.expertiseRepository.findById(expertiseId).orElseThrow(()->new ResourceNotFoundException("Expertise","id",expertiseId));
		expertise.setSkillTitle(expertiseDto.getSkillTitle());
		expertise.setRating(expertiseDto.getRating());
		expertise.setSkillDescription(expertiseDto.getSkillDescription());
		this.expertiseRepository.save(expertise);
		return expertiseDto;
	}

	@Override
	public boolean deleteExpertise(Integer userId, Integer expertiseId) {
		User user = this.userService.getUserById(userId);
		Expertise expertise = this.expertiseRepository.findById(expertiseId).orElseThrow(()->new ResourceNotFoundException("Expertise","id",expertiseId));
		this.expertiseRepository.delete(expertise);
		return true;
	}

}
