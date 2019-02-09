package com.woo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Question;
import com.woo.domain.QuestionScore;
import com.woo.model.QuestionModel;
import com.woo.repository.QuestionScoreRepository;
import com.woo.service.types.QuestionScoreService;
import com.woo.utils.generater.GenerateRandom;

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
		new Thread(new Runnable() {
			@Override
			public void run() {
				QuestionScore questionScore = question.getScore();
				if (result) {
					questionScore.setTrueCount(questionScore.getTrueCount() + 1);
				}
				else {
					questionScore.setFalseCount(questionScore.getFalseCount() + 1);
				}
				addQuestionScore(questionScore);
			}
		}).start();
		;

	}

	@Override
	public void setPercentageValue(QuestionModel questionModel, Question question) {
		if (question.getScore() == null) {
			questionModel.setTotalPercentage(0);
			questionModel.setFalsePercentage(100);
			questionModel.setTruePercentage(0);
			return;
		}
		int trueCount = question.getScore().getTrueCount();
		int falseCount = question.getScore().getFalseCount();
		int totalCount = trueCount + falseCount;
		if (totalCount == 0) {
			trueCount = GenerateRandom.getRandomInt(1, 7);
			falseCount = GenerateRandom.getRandomInt(1, 7);
			totalCount = trueCount + falseCount;
			addQuestionScore(new QuestionScore(trueCount, falseCount, question));
		}
		float truePercentage = ((float) trueCount / totalCount) * 100;
		int falsePercentage = 100 - (int) truePercentage;
		questionModel.setFalsePercentage(falsePercentage);
		questionModel.setTruePercentage((int) truePercentage);
		float totalPercentage = 0;
		if (totalCount < 100) {
			totalPercentage = totalCount;
		}
		else if (totalCount < 500) {
			totalPercentage = ((float) ((float) totalCount / (500))) * 100;
		}
		else if (totalCount < 1000) {
			totalPercentage = ((float) ((float) totalCount / (1000))) * 100;
		}
		else if (totalCount < 10000) {
			totalPercentage = ((float) ((float) totalCount / (10000))) * 100;
		}
		questionModel.setTotalPercentage((int) totalPercentage);
	}

}
