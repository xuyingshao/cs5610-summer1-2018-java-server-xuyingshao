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

import com.example.CourseManager.models.Exam;
import com.example.CourseManager.models.Lesson;
import com.example.CourseManager.models.Widget;
import com.example.CourseManager.repositories.EssayQuestionRepository;
import com.example.CourseManager.repositories.ExamRepository;
import com.example.CourseManager.repositories.FillInTheBlankQuestionRepository;
import com.example.CourseManager.repositories.LessonRepository;
import com.example.CourseManager.repositories.MultipleChoiceQuestionRepository;
import com.example.CourseManager.repositories.QuestionRepository;
import com.example.CourseManager.repositories.TrueOrFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	EssayQuestionRepository essayRepository;
	@Autowired
	MultipleChoiceQuestionRepository multipleChoiceRepository;
	@Autowired
	TrueOrFalseQuestionRepository trueFalseRepository;
	@Autowired
	FillInTheBlankQuestionRepository fillInBlankRepository;
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<Exam> findAllExamsForLesson(@PathVariable("lessonId") int lessonId) {
		List<Exam> exams = new ArrayList<Exam>();
		for (Exam exam : examRepository.findAll()) {
			if (exam.getLesson().getId() == lessonId) {
				exams.add(exam);
			}
		}
		return exams;
	}
	
	@PostMapping("/api/lesson/{lessonId}/exam") 
	public Exam createExam(@PathVariable("lessonId") int lessonId, 
			@RequestBody Exam exam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if (data.isPresent()) {
			Lesson lesson = data.get();
			exam.setLesson(lesson);
			return examRepository.save(exam);
		}
		return null;
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExamById(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
	
	@PutMapping("/api/exam/{examId}")
	public Exam updateExam(@PathVariable("examId") int examId,
			@RequestBody Exam newExam) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			exam.setTitle(newExam.getTitle());
			exam.setDescription(newExam.getDescription());
			return examRepository.save(exam);
		}
		return null;
	}
}
