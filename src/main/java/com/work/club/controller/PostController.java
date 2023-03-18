package com.work.club.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.club.dao.PostRepository;
import com.work.club.entity.Post;
import com.work.club.payloads.ApiResponse;
import com.work.club.payloads.PostDto;
import com.work.club.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	PostRepository postRepository;
	
	
	//createPostBySpecificUser
	@PostMapping("/users/{userId}/addPost")
	public ResponseEntity<?>createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId){
		if(postDto==null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("Post can not be empty !!", false, LocalTime.now()), HttpStatus.BAD_REQUEST);
		}
		PostDto createdPost = this.postService.createPost(postDto, userId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.OK);
	}
	//getPostsOfaUser
	@GetMapping("users/{userId}/posts")
	public ResponseEntity<?>getPostsByUser(@PathVariable("userId") Integer userId){
		List<Post> posts = this.postService.getPostsByUserId(userId);
		if(posts.size()==0) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("No posts exist for this User", false, LocalTime.now()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	//getAllPosts
	@GetMapping("/posts")
	public ResponseEntity<?>getAllPosts(){
		List<Post> posts = this.postService.getAllPosts();
		if(posts.size()==0) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("No Posts Created !!", false, LocalTime.now()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	//deletePostOfUser(a user can only delete his posts not others)
	@DeleteMapping("/users/{userId}/deletePost/{postId}")
	public ResponseEntity<?>deletePost(@PathVariable("userId") Integer userId,  @PathVariable("postId") Integer postId){
		boolean ableTodelete = this.postService.deletePost(postId, userId);
		if(ableTodelete) {
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted Successfully !!!", true,LocalTime.now()),HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("You are not authorized to delete this Post !!", false, LocalTime.now()), HttpStatus.BAD_REQUEST);
	}
	
	//updatePost;
	@PutMapping("/users/{userId}/upatePost/{postId}")
	public ResponseEntity<?>updatePost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId, 
			@PathVariable("postId") Integer postId
			){
		boolean updatedPost = this.postService.updatePost(postDto, postId, userId);
		if(updatedPost) {
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Updated Successfully !!", true, LocalTime.now()), HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("You are not authorized to update this post", false, LocalTime.now()), HttpStatus.BAD_REQUEST);
	}
	
	//getPostByPostId
	@GetMapping("/posts/{postId}")
	public ResponseEntity<?>getPostByPostId(@PathVariable("postId") Integer postId){
		Post post = this.postService.getPostByPostId(postId);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	//searchPostByKeyword
	@GetMapping("/posts/search")
	public ResponseEntity<?>searchPost(@RequestParam("keyword") String keyword){
	  List<Post> foundPosts = this.postService.searchPost(keyword);
	  if(foundPosts.size()==0) {
		  return new ResponseEntity<ApiResponse>(new ApiResponse("No Users found for this search !!", false, LocalTime.now()), HttpStatus.NOT_FOUND);
	  }
	  return new ResponseEntity<List<Post>>(foundPosts, HttpStatus.OK);
	}
	
	//find Posts between dates;
	@GetMapping("/posts/findPosts")
	public ResponseEntity<?>findPostBetweenDates(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate){
		
		//current  date form yyyy-mm-dd; 
		List<Post> result = this.postRepository.findPostsBetweenDates(startDate, endDate);
		if(result.size()==0) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("No post found between these Dates", false, LocalTime.now()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
		
	}
	
	
	
	
}
