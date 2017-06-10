package com.woo.service.types;

import java.util.ArrayList;

import org.springframework.data.domain.Pageable;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.domain.Question;

public interface QuestionService {

	void addQuestion(Question question);

	Iterable<Question> getAllQuestion();

	void deleteQuestionById(long id);

	Question getQuestionById(long id);

	Question getQuestionByItemAndItem(Item itemA, Item itemB);

	ArrayList<Question> getQuestionByCategoryAndLevel(Category category, int level);

	Question getQuestionByCategoryAndLevelWithPage(Category category, int level, Pageable pageable);

	Question getRandomQuestion(Category category, int level);

}
