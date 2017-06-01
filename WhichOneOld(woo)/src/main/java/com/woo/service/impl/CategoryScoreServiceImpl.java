package com.woo.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.domain.Contact;
import com.woo.domain.Ratio;
import com.woo.domain.Score;
import com.woo.domain.Statistic;
import com.woo.model.CategoryScoreModel;
import com.woo.repository.CategoryScoreRepository;
import com.woo.service.types.CategoryScoreService;
import com.woo.utils.Level;

@Service
public class CategoryScoreServiceImpl implements CategoryScoreService {

	private CategoryScoreRepository categoryScoreRepository;
	private CategoryServiceImpl categoryService;
	private ScoreServiceImpl scoreService;

	@Autowired
	public CategoryScoreServiceImpl(CategoryScoreRepository categoryScoreRepository,
			CategoryServiceImpl categoryService, ScoreServiceImpl scoreService) {
		this.categoryScoreRepository = categoryScoreRepository;
		this.categoryService = categoryService;
		this.scoreService = scoreService;

	}

	@Override
	public CategoryScore getCategoryScore(Contact contact, Category category) {
		Statistic statistic = contact.getStatistic();
		for (CategoryScore categoryScore : statistic.getCategoryList()) {
			if (categoryScore.getCategory().getId() == category.getId()) {
				return categoryScore;
			}
		}
		return null;
	}

	@Override
	public CategoryScoreModel getTotalCategoryScore(Contact contact, String categoryName) {

		Iterable<Category> categories = categoryService.getCategoriesByName(categoryName);
		CategoryScoreModel totalScoreModel = null;
		for (Category category : categories) {
			CategoryScore categoryScore = getCategoryScore(contact, category);
			if (categoryScore != null) {
				if (totalScoreModel == null) {
					totalScoreModel = CategoryScoreModel.getCategoryScoreModel(categoryScore);
				} else {
					totalScoreModel.addCategoryScoreModel(CategoryScoreModel.getCategoryScoreModel(categoryScore));
				}
			}
		}
		return totalScoreModel;
	}

	@Override
	public void updateCategoryScoreTable(Category category, int level, Contact contact, boolean answer) {
		CategoryScore categoryScore = getCategoryScore(contact, category);
		if (categoryScore == null) {
			categoryScore = new CategoryScore();
			categoryScore.setCategory(category);
			categoryScore.setLastUpdateDate(Calendar.getInstance().getTime());
			categoryScore.setStatistic(contact.getStatistic());
		}
		Score score = categoryScore.getScore();
		if (score == null) {
			score = new Score(false);
			score = scoreService.addScore(score);
		}
		Ratio ratioLevel = Level.getLevel(score, level);
		if (answer) {
			ratioLevel.increaseTrueCount();
		} else {
			ratioLevel.increaseFalseCount();
		}
		score = scoreService.addScore(score);
		categoryScore.setScore(score);
		categoryScoreRepository.save(categoryScore);
	}
}
