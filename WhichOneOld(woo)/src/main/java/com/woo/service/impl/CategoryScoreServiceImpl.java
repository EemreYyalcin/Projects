package com.woo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.domain.Statistic;
import com.woo.model.CategoryScoreModel;
import com.woo.repository.CategoryScoreRepository;
import com.woo.service.types.CategoryScoreService;

@Service
public class CategoryScoreServiceImpl implements CategoryScoreService {

	private CategoryScoreRepository categoryScoreRepository;
	private CategoryServiceImpl categoryService;
	private StatisticServiceImpl statisticService;

	@Autowired
	public CategoryScoreServiceImpl(CategoryScoreRepository categoryScoreRepository,
			CategoryServiceImpl categoryService, StatisticServiceImpl statisticService) {
		this.categoryScoreRepository = categoryScoreRepository;
		this.categoryService = categoryService;
		this.statisticService = statisticService;

	}

	@Override
	public CategoryScore getCategoryScore(long userId, Category category) {
		Statistic statistic = statisticService.getStatisticByUserId(userId);
		for (CategoryScore categoryScore : statistic.getCategoryList()) {
			if (categoryScore.getCategory().getId() == category.getId()) {
				return categoryScore;
			}
		}
		return null;
	}

	@Override
	public CategoryScoreModel getTotalCategoryScore(long userId, String categoryName) {

		Iterable<Category> categories = categoryService.getCategoriesByName(categoryName);
		CategoryScoreModel totalScoreModel = null;
		for (Category category : categories) {
			CategoryScore categoryScore = getCategoryScore(userId, category);
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
}
