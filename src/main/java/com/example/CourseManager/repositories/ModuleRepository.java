package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.Module;

public interface ModuleRepository
	extends CrudRepository<Module, Integer>{
	
}
