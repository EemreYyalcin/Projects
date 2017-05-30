package com.woo.service.types;

import com.woo.domain.Question;
import com.woo.domain.QuestionScore;

public interface QuestionScoreService {

	void addQuestionScore(QuestionScore questionScore);

	void updateQuestionResult(Question question, boolean result);

}
