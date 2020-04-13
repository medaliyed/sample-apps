package com.ali.postsManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ali.postsManager.entity.Address;
import com.ali.postsManager.entity.Comment;
import com.ali.postsManager.entity.Post;
import com.ali.postsManager.entity.User;
import com.ali.postsManager.repo.AddressRepository;
import com.ali.postsManager.repo.CommentRepository;
import com.ali.postsManager.repo.PostRepository;
import com.ali.postsManager.repo.UserRepository;

@RestController
public class AppController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("/user")
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/user/{id}/address")
	public Address getUserAddress(@PathVariable(value = "id") Integer userId) {

		return addressRepository.findByUserId(userId);
	}

	@PostMapping("/user/{id}/address")
	public Address createUserAddress(@PathVariable(value = "id") Integer userId, @RequestBody Address address)
			throws Exception {

		return userRepository.findById(userId).map(user -> {
			address.setUser(user);
			return addressRepository.save(address);
		}).orElseThrow(() -> new Exception());
	}

	@GetMapping("/post")
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@PostMapping("/user/{id}/post")
	public Post createPost(@PathVariable(value = "id") Integer userId, @RequestBody Post post) throws Exception {
		return userRepository.findById(userId).map(user -> {
			post.setUser(user);
			return postRepository.save(post);
		}).orElseThrow(() -> new Exception());
	}

	@GetMapping("/post/{id}/comment")
	public List<Comment> getAlComments(@PathVariable(value = "id") Integer postId) {
		return commentRepository.findByPostId(postId);
	}

	@PostMapping("/post/{id}/comment")
	public Comment createComment(@PathVariable(value = "id") Integer postId, @RequestBody Comment comment)
			throws Exception {
		return postRepository.findById(postId).map(post -> {
			comment.setPost(post);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new Exception());
	}

}
