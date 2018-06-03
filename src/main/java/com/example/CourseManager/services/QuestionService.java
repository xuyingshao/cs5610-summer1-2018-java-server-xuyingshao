package com.example.CourseManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CourseManager.models.EssayQuestion;
import com.example.CourseManager.models.Exam;
import com.example.CourseManager.models.FillInTheBlankQuestion;
import com.example.CourseManager.models.MultipleChoiceQuestion;
import com.example.CourseManager.models.Question;
import com.example.CourseManager.models.TrueOrFalseQuestion;
import com.example.CourseManager.repositories.EssayQuestionRepository;
import com.example.CourseManager.repositories.ExamRepository;
import com.example.CourseManager.repositories.FillInTheBlankQuestionRepository;
import com.example.CourseManager.repositories.MultipleChoiceQuestionRepository;
import com.example.CourseManager.repositories.QuestionRepository;
import com.example.CourseManager.repositories.TrueOrFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	EssayQuestionRepository essayRepository;
	@Autowired
	MultipleChoiceQuestionRepository multipleChoiceRepository;
	@Autowired
	FillInTheBlankQuestionRepository fillInBlankRepository;
	@Autowired
	TrueOrFalseQuestionRepository trueFalseRepository;
	
	@GetMapping("/api/question")
	public List<Question> findAllQuestions() {
		return (List<Question>) questionRepository.findAll();
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllExamsForLesson(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			List<Question> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/essay")
	public EssayQuestion createEssayQuestion(@PathVariable("examId") int examId,
			@RequestBody EssayQuestion essay) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			essay.setExam(exam);
			return essayRepository.save(essay);
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceQuestion createMultipleChoiceQuestion(@PathVariable("examId") int examId,
			@RequestBody MultipleChoiceQuestion multiChoice) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			multiChoice.setExam(exam);
			return multipleChoiceRepository.save(multiChoice);
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlankQuestion createFillInBlankQuestion(@PathVariable("examId") int examId,
			@RequestBody FillInTheBlankQuestion fillBlank) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			fillBlank.setExam(exam);
			return fillInBlankRepository.save(fillBlank);
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseQuestion createTrueFalseQuestion(@PathVariable("examId") int examId,
			@RequestBody TrueOrFalseQuestion trueFalse) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			trueFalse.setExam(exam);
			return trueFalseRepository.save(trueFalse);
		}
		return null;
	}
	
	@GetMapping("/api/essay/{questionId}")
	public EssayQuestion findEssayById(@PathVariable("questionId") int questionId) {
		Optional<EssayQuestion> data = essayRepository.findById(questionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultipleById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> data = multipleChoiceRepository.findById(questionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/blanks/{questionId}")
	public FillInTheBlankQuestion findFillInBlankById(@PathVariable("questionId") int questionId) {
		Optional<FillInTheBlankQuestion> data = fillInBlankRepository.findById(questionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/truefalse/{questionId}")
	public TrueOrFalseQuestion findTrueFalseById(@PathVariable("questionId") int questionId) {
		Optional<TrueOrFalseQuestion> data = trueFalseRepository.findById(questionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
}
