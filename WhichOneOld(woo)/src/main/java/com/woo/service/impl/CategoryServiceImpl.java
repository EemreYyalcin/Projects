package com.woo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.core.attributes.Link;
import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.domain.Item;
import com.woo.domain.Statistic;
import com.woo.model.CategoryModel;
import com.woo.repository.CategoryRepository;
import com.woo.service.types.CategoryService;
import com.woo.utils.log.LogMessage;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	private ItemServiceImpl itemService;

	private CategoryScoreServiceImpl categoryScoreService;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ItemServiceImpl itemService, CategoryScoreServiceImpl categoryScoreService) {
		this.categoryRepository = categoryRepository;
		this.itemService = itemService;
		this.categoryScoreService = categoryScoreService;
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Iterable<Category> getAllCategories() {
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

	@Override
	public ArrayList<CategoryModel> getCategoriesWithName(Statistic statistic, long userId, boolean isExplore, String token) {
		ArrayList<Category> filterCategories = new ArrayList<Category>();
		ArrayList<String> allCategoriesName = categoryRepository.findDistinctStates();
		ArrayList<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
		if (statistic != null && !isExplore) {
			if (statistic.getCategoryScoreList() == null || statistic.getCategoryScoreList().size() == 0) {
				// empty Page
				return null;
			}
			for (String categoryName : allCategoriesName) {
				for (CategoryScore categoryScore : statistic.getCategoryScoreList()) {
					if (categoryName.equals(categoryScore.getCategory().getName())) {
						filterCategories.add(categoryScore.getCategory());
						break;
					}
				}
			}
		}
		else {
			for (String categoryName : allCategoriesName) {
				filterCategories.add(getCategoriesByName(categoryName).get(0));
			}
		}

		for (Category category : filterCategories) {
			CategoryModel categoryModel = CategoryModel.getCategoryModel(category);
			categoryModel.setDecadeList(getDecades(category.getName()));
			categoryModel.setLastUpdateDate(getLastUpdateDate(category.getName()));
			Item randomItem = itemService.getRandomItem(category);
			categoryModel.setCategoryClickUrl(Link.categories + token + "/");
			categoryModel.setCategoryScoreModel(categoryScoreService.getTotalCategoryScore(userId, category.getName(), this));
			if (randomItem == null || randomItem.getId() == 0) {
				LogMessage.error("Wrong Item Code:Patika");
				continue;
			}
			if (randomItem != null) {
				categoryModel.setImageResource(Link.items + randomItem.getId());
			}
			categoryModels.add(categoryModel);
		}
		categoryModels = sortCategoryModel(categoryModels);

		return categoryModels;
	}

	private ArrayList<CategoryModel> sortCategoryModel(ArrayList<CategoryModel> categoryModels) {
		Collections.sort(categoryModels, new Comparator<CategoryModel>() {
			public int compare(CategoryModel o1, CategoryModel o2) {
				return o1.getLastUpdateDate().after(o2.getLastUpdateDate()) ? -1 : 1;
			}
		});
		return categoryModels;
	}

	@Override
	public ArrayList<CategoryModel> getCategoriesByDecade(String categoryName, long userId, String token) {
		ArrayList<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
		ArrayList<Category> categories = getCategoriesByName(categoryName);
		LogMessage.logx("categories:" + categories.toString());
		for (Category category : categories) {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setCategoryId(category.getId());
			categoryModel.setCategoryName(category.getName());
			categoryModel.setDecade(category.getDecade());
			categoryModel.setLastUpdateDate(category.getLastUpdateDate());
			categoryModel.setCategoryClickUrl(Link.selectLevels + token + "/");
			categoryModel.setCategoryScoreModel(categoryScoreService.getCategoryDecadeScoreModel(userId, category));
			Item randomItem = itemService.getRandomItem(category);
			if (randomItem == null || randomItem.getId() == 0) {
				LogMessage.error("Wrong Item Code:Lana");
				continue;
			}
			if (randomItem != null) {
				categoryModel.setImageResource(Link.items + randomItem.getId());
			}
			categoryModels.add(categoryModel);
		}
		return categoryModels;
	}

}
