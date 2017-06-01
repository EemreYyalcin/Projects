package com.woo.service.types;

import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.domain.Contact;
import com.woo.model.CategoryScoreModel;

public interface CategoryScoreService {

	CategoryScore getCategoryScore(Contact contact, Category category);

	CategoryScoreModel getTotalCategoryScore(Contact contact, String categoryName);

	void updateCategoryScoreTable(Category category, int level, Contact contact, boolean b);

}
