package com.woo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class QuestionScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTIONSCORE_ID", nullable = false, updatable = false)
	private long id;
	@Column(name = "TRUECOUNT")
	private int trueCount;
	@Column(name = "FALSECOUNT")
	private int falseCount;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "score")
	private Question question;

	public QuestionScore() {}
	
	public QuestionScore(int trueCount, int falseCount, Question question) {
		this.trueCount = trueCount;
		this.falseCount = falseCount;
		this.question = question;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTrueCount() {
		return trueCount;
	}

	public void setTrueCount(int trueCount) {
		this.trueCount = trueCount;
	}

	public int getFalseCount() {
		return falseCount;
	}

	public void setFalseCount(int falseCount) {
		this.falseCount = falseCount;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
