package com.woo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Question;
import com.woo.domain.QuestionScore;
import com.woo.repository.QuestionScoreRepository;

@Service
public class QuestionScoreServiceImpl {

	@Autowired
	private QuestionScoreRepository questionScoreRepository;

	public void addQuestionScore(QuestionScore questionScore) {
		questionScoreRepository.save(questionScore);
	}

	public void updateQuestionResult(Question question, boolean result) {
		QuestionScore questionScore = question.getScore();
		if (result) {
			questionScore.setTrueCount(questionScore.getTrueCount() + 1);
		} else {
			questionScore.setFalseCount(questionScore.getFalseCount() + 1);
		}
		addQuestionScore(questionScore);

	}
}
