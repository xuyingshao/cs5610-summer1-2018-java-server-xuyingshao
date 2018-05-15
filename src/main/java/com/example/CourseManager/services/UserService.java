package com.example.CourseManager.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>)repository.findAll();
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		repository.deleteById(userId);;
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		
		if (data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		}
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, 
			@RequestBody User newUser) {
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
	
	public List<User> findUserByUsername(String username) {
		return (List<User>)repository.findUserByUsername(username);
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, 
			HttpSession session,
			HttpServletResponse response) {
		String username = user.getUsername();
		List<User> data = (List<User>)repository.findUserByUsername(username);
		if (data.isEmpty()) {
			session.setAttribute(user.getUsername(), user);
			return repository.save(user);
		}
		else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	}
	
	public List<User> findUserByCredentials(String username, 
			String password) {
		return (List<User>)repository.findUserByCredentials(username, password);
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user, 
			HttpSession session,
			HttpServletResponse response) {
		String username = user.getUsername();
		String password = user.getPassword();
		List<User> data = (List<User>)repository.findUserByCredentials(username, password);
		if (data.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		else {
			User userData = data.get(0);
			session.setAttribute(userData.getUsername(), userData);
			return data.get(0);
		}
	}
	
	@PutMapping("/api/profile/{userId}")
	public User updateProfile(@PathVariable("userId") int id, 
			@RequestBody User user,
			HttpServletResponse response) {
		Optional<User> data = repository.findById(id);
		
		if (data.isPresent()) {
			User old = data.get();
			String phone = user.getPhone();
			String email = user.getEmail();
			String role = user.getRole();
			Date dateOfBirth = user.getDateOfBirth();
			
			if (phone != null) {
				old.setPhone(phone);
			}
			if (email != null) {
				old.setEmail(email);
			}
			if (role != null) {
				old.setRole(role);
			}
			if (dateOfBirth != null) {
				old.setDateOfBirth(dateOfBirth);
			}
			repository.save(old);
			return old;	
		}
		else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}	
	}
}
