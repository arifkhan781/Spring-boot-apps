package com.work.club.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.club.dao.PostRepository;
import com.work.club.entity.Post;
import com.work.club.entity.User;
import com.work.club.exceptions.ResourceNotFoundException;
import com.work.club.payloads.PostDto;
import com.work.club.service.PostService;
import com.work.club.service.UserService;

@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId) {
		User user = this.userService.getUserById(userId);
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setUser(user);
		user.getPosts().add(post);
		post.setDate(LocalDate.now());
		post.setAuthor(user.getUserName());
		this.postRepository.save(post);
		postDto.setAuthor(post.getAuthor());
		return postDto;	
	}

	@Override
	public List<Post> getAllPosts() {
		List<Post> posts = this.postRepository.findAll();
		return posts;
	}

	@Override
	public boolean deletePost(Integer postId, Integer userId) {
		User user = this.userService.getUserById(userId);
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		System.out.println(user.getUserName());
		System.out.println(post.getAuthor());
		if(post.getAuthor().equals(user.getUserName())) {
		postRepository.delete(post);
		return true;
		}
		return false;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		List<Post> posts = this.postRepository.findAll();
		List<Post> resultantPost = posts.stream().filter(post ->post.getTitle().contains(keyword)).collect(Collectors.toList());
		return resultantPost;
	}

	@Override
	public boolean updatePost(PostDto postDto, Integer postId, Integer userId) {
		
		boolean isUpdated = false;
		User user = this.userService.getUserById(userId);
		Post existingPost = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		if(existingPost.getAuthor().equals(user.getUserName())) {
		existingPost.setTitle(postDto.getTitle());
		existingPost.setDescription(postDto.getDescription());
		existingPost.setAuthor(user.getUserName());
		this.postRepository.save(existingPost);
		isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public List<Post> getPostsByUserId(Integer userId) {
		User foundUser = this.userService.getUserById(userId);
		List<Post> posts = foundUser.getPosts();
		return posts;
	}

	@Override
	public Post getPostByPostId(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "postId", postId));
		return post;
	}
	

	
}
