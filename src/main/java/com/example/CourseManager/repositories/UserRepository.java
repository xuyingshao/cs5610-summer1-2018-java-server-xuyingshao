package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
