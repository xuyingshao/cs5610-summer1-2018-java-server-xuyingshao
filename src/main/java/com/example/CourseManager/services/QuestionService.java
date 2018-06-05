package com.example.CourseManager.services;

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
	
	@DeleteMapping("/api/essay/{questionId}") 
	public void deleteEssayQuestion(@PathVariable("questionId") int questionId) {
		essayRepository.deleteById(questionId);
	}
	
	@DeleteMapping("/api/multi/{questionId}") 
	public void deleteMultipleChoiceQuestion(@PathVariable("questionId") int questionId) {
		multipleChoiceRepository.deleteById(questionId);
	}
	
	@DeleteMapping("/api/blanks/{questionId}")
	public void deleteFillInBlankQuestion(@PathVariable("questionId") int questionId) {
		fillInBlankRepository.deleteById(questionId);
	}
	
	@DeleteMapping("/api/truefalse/{questionId}")
	public void deleteTrueFalseQuestion(@PathVariable("questionId") int questionId) {
		trueFalseRepository.deleteById(questionId);
	}
	
	@PutMapping("/api/essay/{questionId}") 
	public EssayQuestion updateEssayQuestion(@PathVariable("questionId") int questionId,
			@RequestBody EssayQuestion newQuestion) {
		Optional<EssayQuestion> data = essayRepository.findById(questionId);
		if (data.isPresent()) {
			EssayQuestion essay = data.get();
			essay.setTitle(newQuestion.getTitle());
			essay.setDescription(newQuestion.getDescription());
			essay.setPoints(newQuestion.getPoints());
			return essayRepository.save(essay);
		}
		return null;
	}
	
	@PutMapping("/api/multi/{questionId}") 
	public MultipleChoiceQuestion updateMultipleChoiceQuestion(@PathVariable("questionId") int questionId,
			@RequestBody MultipleChoiceQuestion newQuestion) {
		Optional<MultipleChoiceQuestion> data = multipleChoiceRepository.findById(questionId);
		if (data.isPresent()) {
			MultipleChoiceQuestion multi = data.get();
			multi.setTitle(newQuestion.getTitle());
			multi.setDescription(newQuestion.getDescription());
			multi.setPoints(newQuestion.getPoints());
			multi.setChoices(newQuestion.getChoices());
			multi.setCorrectAnswer(newQuestion.getCorrectAnswer());
			return multipleChoiceRepository.save(multi);
		}
		return null;
	}
	
	@PutMapping("/api/blanks/{questionId}")
	public FillInTheBlankQuestion updateFillInBlankQuestion(@PathVariable("questionId") int questionId,
			@RequestBody FillInTheBlankQuestion newQuestion) {
		Optional<FillInTheBlankQuestion> data = fillInBlankRepository.findById(questionId);
		if (data.isPresent()) {
			FillInTheBlankQuestion fill = data.get();
			fill.setTitle(newQuestion.getTitle());
			fill.setDescription(newQuestion.getDescription());
			fill.setPoints(newQuestion.getPoints());
			fill.setVariables(newQuestion.getVariables());
			return fillInBlankRepository.save(fill);
		}
		return null;
	}
	
	@PutMapping("/api/truefalse/{questionId}")
	public TrueOrFalseQuestion updateTrueFalseQuestion(@PathVariable("questionId") int questionId,
			@RequestBody TrueOrFalseQuestion newQuestion) {
		Optional<TrueOrFalseQuestion> data = trueFalseRepository.findById(questionId);
		if (data.isPresent()) {
			TrueOrFalseQuestion trueFalse = data.get();
			trueFalse.setTitle(newQuestion.getTitle());
			trueFalse.setDescription(newQuestion.getDescription());
			trueFalse.setPoints(newQuestion.getPoints());
			trueFalse.setIsTrue(newQuestion.getIsTrue());
			return trueFalseRepository.save(trueFalse);
		}
		return null;
	}
}
