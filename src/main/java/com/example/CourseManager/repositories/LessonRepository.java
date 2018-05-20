package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.Lesson;

public interface LessonRepository 
	extends CrudRepository<Lesson, Integer> {

}
