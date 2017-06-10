package com.woo.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.domain.Question;
import com.woo.repository.QuestionRepository;
import com.woo.service.types.QuestionService;
import com.woo.utils.generater.GenerateRandom;
import com.woo.utils.log.LogMessage;

@Service
public class QuestionServiceImpl implements QuestionService {

	private QuestionRepository questionRepository;

	@Autowired
	public QuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	@Override
	public void addQuestion(Question question) {
		questionRepository.save(question);
	}

	@Override
	public Iterable<Question> getAllQuestion() {
		return questionRepository.findAll();
	}

	@Override
	public void deleteQuestionById(long id) {
		questionRepository.delete(id);
	}

	@Override
	public Question getQuestionById(long id) {
		return questionRepository.findById(id);
	}

	@Override
	public Question getQuestionByItemAndItem(Item itemA, Item itemB) {
		return questionRepository.findByItemAAndItemB(itemA, itemB);
	}

	@Override
	public ArrayList<Question> getQuestionByCategoryAndLevel(Category category, int level) {
		return questionRepository.findByCategoryAndLevel(category, level);
	}

	@Override
	public Question getQuestionByCategoryAndLevelWithPage(Category category, int level, Pageable pageable) {
		ArrayList<Question> questions = questionRepository.findByCategoryAndLevel(category, level, pageable);
		if (questions == null || questions.size() == 0) {
			return null;
		}
		return questions.get(0);
	}
	
	
	@Override
	public Question getRandomQuestion(Category category, int level) {
		ArrayList<Question> questions = getQuestionByCategoryAndLevel(category, level);
		if (questions.size() <= 0) {
			LogMessage.error("No Questions!! Code:Accessor");
			return null;
		}
		int randomValue = GenerateRandom.getRandomInt(0, questions.size());
		return questions.get(randomValue);
	}

	

}
