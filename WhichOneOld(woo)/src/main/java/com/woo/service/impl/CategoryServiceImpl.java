package com.woo.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Category;
import com.woo.repository.CategoryRepository;
import com.woo.service.types.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	@Override
	public Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}
	@Override
	public void deleteCategoriesById(long id) {
		categoryRepository.delete(id);
	}
	@Override
	public Category getCategoryById(long id) {
		return categoryRepository.findById(id);
	}
	@Override
	public ArrayList<Category> getCategoriesByName(String name) {
		return categoryRepository.findByNameLike(name);
	}
	@Override
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
	@Override
	public ArrayList<Integer> getDecades(String categoryName) {
		ArrayList<Category> categories = getCategoriesByName(categoryName);
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < categories.size(); i++) {
			list.add(categories.get(i).getDecade());
		}
		return list;
	}
	@Override
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
