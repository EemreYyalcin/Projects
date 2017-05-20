package com.woo.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Category;
import com.woo.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	public Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}

	public void deleteCategoriesById(long id) {
		categoryRepository.delete(id);
	}

	public Category getCategoryById(long id) {
		return categoryRepository.findById(id);
	}

	public ArrayList<Category> getCategoriesByName(String name) {
		return categoryRepository.findByNameLike(name);
	}

	public Category getCategoryByNameAndDecade(String name, int decade) {
		Iterable<Category> categoryList = categoryRepository.findByNameAndDecade(name, decade);
		if (categoryList == null) {
			return null;
		}
		if (!categoryList.iterator().hasNext()) {
			return null;
		}
		return categoryList.iterator().next();
	}

	public ArrayList<Integer> getDecades(String categoryName) {
		ArrayList<Category> categories = getCategoriesByName(categoryName);
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < categories.size(); i++) {
			list.add(categories.get(i).getDecade());
		}
		return list;
	}

	public Date getLastUpdateDate(String categoryName) {
		ArrayList<Category> categories = getCategoriesByName(categoryName);
		if (categories.size() <= 0) {
			return null;
		}
		Date lastUpdateDate = categories.get(0).getLastUpdateDate();
		for (Category category : categories) {
			if (!lastUpdateDate.after(category.getLastUpdateDate())) {
				lastUpdateDate = category.getLastUpdateDate();
			}
		}
		return lastUpdateDate;
	}

}
