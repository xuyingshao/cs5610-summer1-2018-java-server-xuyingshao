package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.Exam;

public interface ExamRepository
extends CrudRepository<Exam, Integer> {

}
