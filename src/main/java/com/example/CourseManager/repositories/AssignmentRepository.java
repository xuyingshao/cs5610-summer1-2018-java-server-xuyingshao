package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.Assignment;

public interface AssignmentRepository 
extends CrudRepository<Assignment, Integer> {

}
