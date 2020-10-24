package com.java.SpringbootRest.users;

import java.net.URI;
import java.util.List;

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
import org.springframework.hateoas.*;

@RestController
public class UserController {

	@Autowired
	private UserServiceDao UserServiceDao;

	@GetMapping("/User")
	public List<User> getAllUser() {
		return UserServiceDao.findAllUser();
	}

	@GetMapping("/User/{id}")
	public User getUser(@PathVariable int id) {
		User user = UserServiceDao.findOne(id);

		if (user == null)
			throw new UserNotFoundException("id - " + id);
		
		return user;
	}

	@DeleteMapping("/User/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = UserServiceDao.deleteById(id);

		if (user == null)
			throw new UserNotFoundException("id - " + id);

		//return ResponseEntity.noContent().build();

	}

	@PostMapping("/User")
	public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
		User savedUser = UserServiceDao.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

}
