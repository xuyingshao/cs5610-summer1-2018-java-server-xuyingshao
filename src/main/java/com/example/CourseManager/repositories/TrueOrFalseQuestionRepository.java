package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.TrueOrFalseQuestion;

public interface TrueOrFalseQuestionRepository
extends CrudRepository<TrueOrFalseQuestion, Integer> {

}
