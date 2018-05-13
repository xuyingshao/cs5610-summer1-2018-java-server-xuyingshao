package com.example.CourseManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CourseManager.models.User;
import com.example.CourseManager.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@GetMapping("api/user")
	public List<User> findAllUsers() {
		return (List<User>)repository.findAll();
	}
	
	@PostMapping("api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@DeleteMapping("api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		repository.deleteById(userId);;
	}
	
	@GetMapping("api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		
		if (data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		}
	}
	
	@PutMapping("api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		
		if (data.isPresent()) {
			User user = data.get();
			String username = newUser.getUsername();
			String firstName = newUser.getFirstName();
			String lastName = newUser.getLastName();
			String role = newUser.getRole();
			
			if (username != null) {
				user.setUsername(username);
			}
			if (firstName != null) {
				user.setFirstName(firstName);
			}
			if (lastName != null) {
				user.setLastName(lastName);
			}
			if (role != null) {
				user.setRole(role);
			}
			repository.save(user);
			return user;
		}
		return null;
	}	
}
