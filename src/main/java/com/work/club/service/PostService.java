package com.work.club.service;

import java.util.List;

import com.work.club.entity.Post;
import com.work.club.payloads.PostDto;

public interface PostService {

	//create a post;
	PostDto createPost(PostDto postDto, Integer userId);
	//getPostsByUserId;
	List<Post>getPostsByUserId(Integer userId);
	//getAllPost
	List<Post>getAllPosts();
	//deletePost
	boolean deletePost(Integer postId, Integer userId);
	//searchPost
	List<Post>searchPost(String keyword);
	//updatePost
	boolean updatePost(PostDto postDto, Integer postId, Integer userId);
	//getPostByPostId;
	Post getPostByPostId(Integer postId);
	
	
}
