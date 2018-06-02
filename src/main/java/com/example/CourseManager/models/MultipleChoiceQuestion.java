package com.example.CourseManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MULTIPLE_CHOICE_PER_CLASS")
public class MultipleChoiceQuestion extends Question {
	@Column(name = "CHOICES", nullable = false)
	private String choices;
	@Column(name = "CORRECT_ANSWER", nullable = false)
	private int correctAnswer;
	public String getChoices() {
		return choices;
	}
	public void setChoices(String choices) {
		this.choices = choices;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
