package com.java.SpringbootRest.users;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.annotation.Priority;
import javax.websocket.server.PathParam;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.java.SpringbootRest.post.Post;
import com.java.SpringbootRest.post.PostRepository;

import org.springframework.hateoas.*;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public User getUser(@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id - " + id);
		
		return user.get();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		 userRepository.deleteById(id);
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retriveAllUsers(@PathVariable int id){
		Optional<User> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}
		return userOptional.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<User> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}
		
		post.setUser(userOptional.get());
		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(userOptional.get().getId()).toUri();
		
		return ResponseEntity.created(location).build();

	}
}
