package com.woo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Question;
import com.woo.domain.QuestionScore;
import com.woo.repository.QuestionScoreRepository;
import com.woo.service.types.QuestionScoreService;

@Service
public class QuestionScoreServiceImpl implements QuestionScoreService {

	@Autowired
	private QuestionScoreRepository questionScoreRepository;
	@Override
	public void addQuestionScore(QuestionScore questionScore) {
		questionScoreRepository.save(questionScore);
	}
	@Override
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
