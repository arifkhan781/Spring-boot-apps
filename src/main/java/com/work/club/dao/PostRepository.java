package com.work.club.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.club.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	//to filter the posts between two dates;
	@Query(value = "SELECT * FROM post  where date between :startDate and :endDate", nativeQuery = true)
	public List<Post>findPostsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
