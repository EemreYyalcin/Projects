package com.woo.service.types;

import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.model.CategoryScoreModel;

public interface CategoryScoreService {

	CategoryScore getCategoryScore(long userId, Category category);

	CategoryScoreModel getTotalCategoryScore(long userId, String categoryName);

}
