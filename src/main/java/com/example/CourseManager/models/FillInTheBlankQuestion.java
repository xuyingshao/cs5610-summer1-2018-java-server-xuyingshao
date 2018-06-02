package com.example.CourseManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FILL_IN_THE_BLANK_PER_CLASS")
public class FillInTheBlankQuestion extends Question {
	@Column(name = "VARIABLES", nullable = false)
	private String variables;

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}
}
