package com.woo.service.types;

import java.util.ArrayList;
import java.util.Date;

import com.woo.domain.Category;

public interface CategoryService {

	void addCategory(Category category);

	Iterable<Category> getCategories();

	void deleteCategoriesById(long id);

	Category getCategoryById(long id);

	ArrayList<Category> getCategoriesByName(String name);

	Category getCategoryByNameAndDecade(String name, int decade);

	ArrayList<Integer> getDecades(String categoryName);

	Date getLastUpdateDate(String categoryName);

}
