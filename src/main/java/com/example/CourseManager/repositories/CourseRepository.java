package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.Course;

public interface CourseRepository 
	extends CrudRepository<Course, Integer> {

}
