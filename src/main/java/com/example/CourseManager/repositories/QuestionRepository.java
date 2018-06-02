package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.Question;

public interface QuestionRepository
extends CrudRepository<Question, Integer> {

}
