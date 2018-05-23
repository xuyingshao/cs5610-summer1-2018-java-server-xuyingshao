package com.example.CourseManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CourseManager.models.Course;
import com.example.CourseManager.models.Module;
import com.example.CourseManager.repositories.CourseRepository;
import com.example.CourseManager.repositories.LessonRepository;
import com.example.CourseManager.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") int courseId,
			@RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);
		if (data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			return moduleRepository.save(newModule);
		}
		return null;	
	}
	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable("moduleId") int moduleId) {
//		Optional<Module> data = moduleRepository.findById(moduleId);
//		if (data.isPresent()) {
//			Module module = data.get();
//			List<Lesson> lessons = module.getLessons();
//			for (Lesson lesson : lessons) {
//				lessonRepository.deleteById(lesson.getId());
//			}
//		}	
		moduleRepository.deleteById(moduleId);
	}
	
	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		return (List<Module>)moduleRepository.findAll();
	}
	
	@GetMapping("/api/module/{id}")
	public Module findModuleById(@PathVariable("id") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if (data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;
	}
	
	@PutMapping("/api/module/{id}")
	public Module updateModule(@PathVariable("id") int id,
			Module newModule) {
		Optional<Module> data = moduleRepository.findById(id);
		if (data.isPresent()) {
			Module module = data.get();
			if (newModule.getTitle() != null) {
				module.setTitle(newModule.getTitle());
			}
			if (newModule.getCourse() != null) {
				module.setCourse(newModule.getCourse());
			}
			return moduleRepository.save(module);
		}
		return null;
	}
}
