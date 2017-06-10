package com.woo.service.types;

import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.domain.Statistic;
import com.woo.model.CategoryScoreModel;
import com.woo.service.impl.CategoryServiceImpl;

public interface CategoryScoreService {

	CategoryScore getCategoryScore(long userId, Category category);

	CategoryScoreModel getTotalCategoryScore(long userId, String categoryName, CategoryServiceImpl categoryService);

	CategoryScore updateCategoryScoreTable(Category category, int level, long userID, boolean b);

	CategoryScoreModel getCategoryDecadeScoreModel(long userId, Category category);

	CategoryScoreModel getTotalScoreProfile(Statistic statistic);

}
