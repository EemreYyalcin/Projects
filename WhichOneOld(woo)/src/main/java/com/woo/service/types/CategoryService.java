package com.woo.service.types;

import java.util.ArrayList;
import java.util.Date;

import com.woo.domain.Category;
import com.woo.domain.Statistic;
import com.woo.model.CategoryModel;

public interface CategoryService {

	void addCategory(Category category);

	Iterable<Category> getAllCategories();

	void deleteCategoriesById(long id);

	Category getCategoryById(long id);

	ArrayList<Category> getCategoriesByName(String name);

	Category getCategoryByNameAndDecade(String name, int decade);

	ArrayList<Integer> getDecades(String categoryName);

	Date getLastUpdateDate(String categoryName);

	ArrayList<CategoryModel> getCategoriesByDecade(String categoryName, long userId);

	ArrayList<CategoryModel> getCategoriesWithName(Statistic statistic);

}
