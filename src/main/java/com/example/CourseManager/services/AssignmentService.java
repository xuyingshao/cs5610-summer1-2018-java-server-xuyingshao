package com.example.CourseManager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CourseManager.models.Assignment;
import com.example.CourseManager.models.Lesson;
import com.example.CourseManager.models.Widget;
import com.example.CourseManager.repositories.AssignmentRepository;
import com.example.CourseManager.repositories.ExamRepository;
import com.example.CourseManager.repositories.LessonRepository;
import com.example.CourseManager.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	AssignmentRepository assignmentRepository;
	@Autowired
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) assignmentRepository.findAll();
	}
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		Optional<Assignment> data = assignmentRepository.findById(assignmentId);
		
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public List<Assignment> findAllAssignmentsForLesson(@PathVariable("lessonId") int lessonId) {
		List<Assignment> assignments = new ArrayList<Assignment>();
		for (Assignment assignment : assignmentRepository.findAll()) {
			if (assignment.getLesson().getId() == lessonId) {
				assignments.add(assignment);
			}
		}
		return assignments;
	}
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public Assignment createAssignment(@PathVariable("lessonId") int lessonId,
			@RequestBody Assignment assignment) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if (data.isPresent()) {
			Lesson lesson = data.get();
			assignment.setLesson(lesson);
			widgetRepository.save(assignment);
//			return assignmentRepository.save(assignment);
			return assignment;
		}
		return null;
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}
	
	@PutMapping("/api/assignment/{assignmentId}")
	public Assignment updateAssignment(@PathVariable("assignmentId") int assignmentId,
			@RequestBody Assignment newAssignment) {
		Optional<Assignment> data = assignmentRepository.findById(assignmentId);
		
		if (data.isPresent()) {
			Assignment assignment = data.get();
			
			assignment.setTitle(newAssignment.getTitle());
			assignment.setDescription(newAssignment.getDescription());
			assignment.setPoints(newAssignment.getPoints());
			
			assignmentRepository.save(assignment);
			return assignment;
		}
		return null;
	}
}
