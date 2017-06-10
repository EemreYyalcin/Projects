package com.woo.service.types;

import com.woo.domain.Question;
import com.woo.domain.QuestionScore;
import com.woo.model.QuestionModel;

public interface QuestionScoreService {

	void addQuestionScore(QuestionScore questionScore);

	void updateQuestionResult(Question question, boolean result);

	void setPercentageValue(QuestionModel questionModel, Question question);

}
