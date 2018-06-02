package com.example.CourseManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TRUE_OR_FALSE_PER_CLASS")
public class TrueOrFalseQuestion extends Question {
	@Column(name = "IS_TRUE", nullable = false)
	private Boolean isTrue;

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
}
