package com.woo.controller.functions;

import java.util.List;

import com.woo.domain.CategoryScore;
import com.woo.domain.Statistic;
import com.woo.model.CategoryScoreModel;

public class ProfileFuctions {

	public static CategoryScoreModel getTotalScoreProfile(Statistic statistic) {

		if (statistic == null) {
			return CategoryScoreModel.getEmptyCategoryScoreModel(1);
		}
		List<CategoryScore> categoryScores = statistic.getCategoryList();
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
