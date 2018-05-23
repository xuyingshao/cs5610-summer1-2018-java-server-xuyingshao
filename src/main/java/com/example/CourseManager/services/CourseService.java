package com.example.CourseManager.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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
import com.example.CourseManager.repositories.CourseRepository;
import com.example.CourseManager.repositories.LessonRepository;
import com.example.CourseManager.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/course")
	public List<Course> findAllCourses() {
		return (List<Course>)courseRepository.findAll();
	}
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int courseId) {		
		courseRepository.deleteById(courseId);
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if (data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		}
	}
	
	@PutMapping("/api/course")
	public Course updateCourse(@RequestBody Course newCourse,
			HttpServletResponse response) {
		Optional<Course> data = courseRepository.findById(newCourse.getId());
		if (data.isPresent()) {
			Course course = data.get();
			if (newCourse.getTitle() != null) {
				course.setTitle(newCourse.getTitle());
			}
			if (newCourse.getModified() != null) {
				course.setModified(newCourse.getModified());
			}
			return courseRepository.save(course);
		}
		else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	}
}
