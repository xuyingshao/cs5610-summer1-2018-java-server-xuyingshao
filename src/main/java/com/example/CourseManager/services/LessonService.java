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

import com.example.CourseManager.models.Lesson;
import com.example.CourseManager.models.Module;
import com.example.CourseManager.repositories.LessonRepository;
import com.example.CourseManager.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable("moduleId") int moduleId,
			@RequestBody Lesson lesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if (data.isPresent()) {
			Module module = data.get();
			lesson.setModule(module);
			return lessonRepository.save(lesson);
		}
		return null;
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int lessonId) {
		lessonRepository.deleteById(lessonId);
	}
	
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		return (List<Lesson>) lessonRepository.findAll();
	}
	
	@GetMapping("/api/lesson/{lessonId}")
	public Lesson findLessonById(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("moduleId") int moduleId,
			HttpServletResponse response) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if (data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@PutMapping("/api/lesson/{lessonId}")
	public Lesson updateLesson(@PathVariable("lessonId") int lessonId,
			Lesson newLesson) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			if (newLesson.getTitle() != null) {
				lesson.setTitle(newLesson.getTitle());
			}
			if (newLesson.getModule() != null) {
				lesson.setModule(newLesson.getModule());
			}
			return lessonRepository.save(lesson);
		}
		return null;
	}
}
