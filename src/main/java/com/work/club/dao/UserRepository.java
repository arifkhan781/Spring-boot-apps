package com.work.club.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.club.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

}
