package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.Hello;

public interface HelloRepository 
	extends CrudRepository<Hello, Integer>{

}
