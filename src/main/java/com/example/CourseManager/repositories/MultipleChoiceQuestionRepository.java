package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.MultipleChoiceQuestion;

public interface MultipleChoiceQuestionRepository
extends CrudRepository<MultipleChoiceQuestion, Integer>{

}
