package com.woo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.domain.Question;
import com.woo.repository.QuestionRepository;

@Service
public class QuestionServiceImpl {

	private QuestionRepository questionRepository;

	@Autowired
	public QuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	public void addQuestion(Question question) {
		questionRepository.save(question);
	}

	public Iterable<Question> getQuestions() {
		return questionRepository.findAll();
	}

	public void deleteQuestionById(long id) {
		questionRepository.delete(id);
	}

	public Question getQuestionById(long id) {
		return questionRepository.findById(id);
	}

	public Question getQuestionByItemAndItem(Item itemA, Item itemB) {
		return questionRepository.findByItemAAndItemB(itemA, itemB);
	}

	public ArrayList<Question> getQuestionByCategoryAndLevel(Category category, int level) {
		return questionRepository.findByCategoryAndLevel(category, level);
	}

}
