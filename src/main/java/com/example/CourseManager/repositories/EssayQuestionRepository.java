package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.EssayQuestion;

public interface EssayQuestionRepository 
extends CrudRepository<EssayQuestion, Integer> {

}
