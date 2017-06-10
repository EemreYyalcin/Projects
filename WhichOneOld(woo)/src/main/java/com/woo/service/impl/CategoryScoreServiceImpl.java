package com.woo.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.core.attributes.Codes;
import com.woo.core.stage.Level;
import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.domain.Ratio;
import com.woo.domain.Score;
import com.woo.domain.Statistic;
import com.woo.model.CategoryScoreModel;
import com.woo.repository.CategoryScoreRepository;
import com.woo.service.types.CategoryScoreService;
import com.woo.utils.log.LogMessage;

@Service
public class CategoryScoreServiceImpl implements CategoryScoreService {

	private CategoryScoreRepository categoryScoreRepository;

	private ScoreServiceImpl scoreService;

	private StatisticServiceImpl statisticService;

	@Autowired
	public CategoryScoreServiceImpl(CategoryScoreRepository categoryScoreRepository, ScoreServiceImpl scoreService, StatisticServiceImpl statisticService) {
		this.categoryScoreRepository = categoryScoreRepository;
		this.scoreService = scoreService;
		this.statisticService = statisticService;

	}

	@Override
	public CategoryScore getCategoryScore(long userId, Category category) {
		Statistic statistic = statisticService.getStatisticByUserId(userId);
		if (statistic.getCategoryScoreList() == null) {
			return null;
		}
		for (CategoryScore categoryScore : statistic.getCategoryScoreList()) {
			if (categoryScore.getCategory().getId() == category.getId()) {
				return categoryScore;
			}
		}
		return null;
	}

	@Override
	public CategoryScoreModel getTotalCategoryScore(long userId, String categoryName, CategoryServiceImpl categoryService) {

		if (userId == Codes.errorIntCode) {
			return null;
		}
		Iterable<Category> categories = categoryService.getCategoriesByName(categoryName);
		CategoryScoreModel totalScoreModel = null;
		int totalQuestionCount = 0;
		for (Category category : categories) {
			totalQuestionCount += category.getMapCountQuestion();
			CategoryScore categoryScore = getCategoryScore(userId, category);
			if (categoryScore != null) {
				if (totalScoreModel == null) {
					totalScoreModel = CategoryScoreModel.getCategoryScoreModel(categoryScore);
				}
				else {
					totalScoreModel.addCategoryScoreModel(CategoryScoreModel.getCategoryScoreModel(categoryScore));
				}
			}
		}
		if (totalScoreModel == null) {
			totalScoreModel = CategoryScoreModel.getEmptyCategoryScoreModel(totalQuestionCount);
		}
		return totalScoreModel;
	}

	@Override
	public CategoryScoreModel getCategoryDecadeScoreModel(long userId, Category category) {
		if (userId == Codes.errorIntCode) {
			return null;
		}
		CategoryScore categoryScore = getCategoryScore(userId, category);
		if (categoryScore == null) {
			return CategoryScoreModel.getEmptyCategoryScoreModel(category.getMapCountQuestion());
		}
		return CategoryScoreModel.getCategoryScoreModel(categoryScore);
	}

	@Override
	public CategoryScore updateCategoryScoreTable(Category category, int level, long userId, boolean answer) {
		CategoryScore categoryScore = getCategoryScore(userId, category);
		Score score = null;
		LogMessage.logx("CategoryScore " + categoryScore);
		if (categoryScore == null) {
			categoryScore = new CategoryScore();
			categoryScore.setCategory(category);
			categoryScore.setLastUpdateDate(Calendar.getInstance().getTime());
			categoryScore.setStatistic(statisticService.getStatisticByUserId(userId));
			categoryScore.setScore(new Score());
		}
		score = categoryScore.getScore();
		Ratio ratioLevel = Level.getLevel(score, level);
		if (answer) {
			ratioLevel.increaseTrueCount();
		}
		else {
			ratioLevel.increaseFalseCount();
		}
		score = scoreService.addScore(score);
		categoryScore.increasePageId(level);
		categoryScore = categoryScoreRepository.save(categoryScore);
		return categoryScore;
	}

	@Override
	public CategoryScoreModel getTotalScoreProfile(Statistic statistic) {

		if (statistic == null) {
			return CategoryScoreModel.getEmptyCategoryScoreModel(1);
		}
		List<CategoryScore> categoryScores = statistic.getCategoryScoreList();
		if (categoryScores == null || categoryScores.size() == 0) {
			return CategoryScoreModel.getEmptyCategoryScoreModel(1);
		}
		CategoryScoreModel totalCategoryScoreModel = null;
		for (CategoryScore categoryScore : categoryScores) {
			CategoryScoreModel categoryScoreModel = CategoryScoreModel.getCategoryScoreModel(categoryScore);
			if (categoryScoreModel == null) {
				continue;
			}
			if (totalCategoryScoreModel == null) {
				totalCategoryScoreModel = categoryScoreModel;
				continue;
			}
			totalCategoryScoreModel = totalCategoryScoreModel.addCategoryScoreModel(categoryScoreModel);
		}

		if (totalCategoryScoreModel == null) {
			return CategoryScoreModel.getEmptyCategoryScoreModel(1);
		}

		return totalCategoryScoreModel;
	}

}
