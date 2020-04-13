package com.ali.postsManager;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.ali.postsManager.entity.Address;
import com.ali.postsManager.entity.Comment;
import com.ali.postsManager.entity.Post;
import com.ali.postsManager.entity.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostsManagerApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	void contextLoads() {
		User user = new User();
		user.setName("Heello mario");
		ResponseEntity<User> responseEntity  = restTemplate.postForEntity("http://localhost:"+
		port+"/user", user, User.class);
		System.out.println("==================== create new user");
		System.out.println(responseEntity.getBody());
		//create address
		int userId = responseEntity.getBody().getId();
		Address address = new Address();
		address.setAddress("old town road");
		address.setPostAddress("fifth avenue");
		
		String url = "http://localhost:"+port+"/user/"+userId+"/address";
		ResponseEntity<Address> addressResponseEntity = restTemplate.postForEntity(url, address, Address.class);
		System.out.println("==================== create new address");
		System.out.println(addressResponseEntity.getBody());
		
		//get user address
		ResponseEntity<Address> responseEnt = restTemplate.getForEntity(url,Address.class);
		System.out.println("=================== get user address");
		System.out.println(responseEnt.getBody());
		
		System.out.println("=============== create post");
		url = "http://localhost:"+port+"/user/"+userId+"/post";
		Post post = new Post();
		post.setPostTitle("the title of the post");
		post.setPostContent("hello this is the content");
		ResponseEntity<Post> postResponseEntity  = restTemplate.postForEntity(url,post, Post.class);
		int postId = postResponseEntity.getBody().getId();
		
		
		System.out.println("=======================ccreate some comment");
		Comment comment = new Comment();
		comment.setComment("comment for " + postResponseEntity.getBody().getPostTitle()+"  number one");
		url = "http://localhost:"+port+"/post/"+postId+"/comment";
		restTemplate.postForEntity(url, comment, Comment.class);
		comment = new Comment();
		comment.setComment("second comment");
		restTemplate.postForEntity(url, comment, Comment.class);
		
		System.out.println("================================== get post");
		
		url = "http://localhost:"+port+"/post/";
		ResponseEntity<List<Post>> listResponseEntity = restTemplate.exchange(url, HttpMethod.GET, 
				null, new ParameterizedTypeReference<List<Post>>() {
				});
		System.out.println(listResponseEntity.getBody());
		
		
		
		postId = listResponseEntity.getBody().get(0).getId();
		System.out.println("=========================== get post comment");
		
		url = "http://localhost:"+port+"/post/"+postId+"/comment";
		ResponseEntity<List<Comment>> listResponseEntity1 = restTemplate.exchange(url, HttpMethod.GET, null,
				
				new ParameterizedTypeReference<List<Comment>>() {
				});
		System.out.println(listResponseEntity1.getBody());
		
		
 	}
	

}
