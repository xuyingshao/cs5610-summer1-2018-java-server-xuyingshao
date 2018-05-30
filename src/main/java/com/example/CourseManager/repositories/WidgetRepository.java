package com.example.CourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseManager.models.Widget;

public interface WidgetRepository 
extends CrudRepository<Widget, Integer> {

}
