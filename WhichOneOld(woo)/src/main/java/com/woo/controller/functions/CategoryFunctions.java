package com.woo.controller.functions;

import java.util.ArrayList;
import java.util.Properties;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.model.CategoryModel;
import com.woo.service.CategoryServiceImpl;
import com.woo.service.ItemServiceImpl;
import com.woo.utils.LogMessage;

public class CategoryFunctions {

	public static ArrayList<CategoryModel> getCategoriesByName(Iterable<Category> categories, ItemServiceImpl itemService, CategoryServiceImpl categoryService) {
		ArrayList<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
		Properties categoryNames = new Properties();
		for (Category category : categories) {
			if (categoryNames.get(category.getName()) != null) {
				continue;
			}
			CategoryModel categoryModel = CategoryModel.getCategoryModel(category);
			categoryModel.setDecadeList(categoryService.getDecades(category.getName()));
			categoryModel.setLastUpdateDate(categoryService.getLastUpdateDate(category.getName()));
			Item randomItem = ItemFunctions.getRandomItem(itemService, category);
			categoryModel.setCategoryClickUrl("/woo/category/");
			if (randomItem == null || randomItem.getId() == 0) {
				LogMessage.error("Wrong Item Code:Patika");
				continue;
			}
			if (randomItem != null) {
				categoryModel.setImageResource("/woo/files/" + randomItem.getId());
			}
			categoryNames.put(category.getName(), categoryModel);
		}

		Iterable<Object> listKeys = categoryNames.keySet();

		for (Object object : listKeys) {
			String key = (String) object;
			categoryModels.add((CategoryModel) categoryNames.get(key));
		}
		return categoryModels;
	}

	public static ArrayList<CategoryModel> getCategoriesByDecade(ArrayList<Category> categories, ItemServiceImpl itemService) {
		ArrayList<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
		LogMessage.logx("categories:" + categories.toString());
		for (Category category : categories) {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setCategoryId(category.getId());
			categoryModel.setCategoryName(category.getName());
			categoryModel.setDecade(category.getDecade());
			categoryModel.setLastUpdateDate(category.getLastUpdateDate());
			categoryModel.setCategoryClickUrl("/woo/question/getlevel/");
			Item randomItem = ItemFunctions.getRandomItem(itemService, category);
			if (randomItem == null || randomItem.getId() == 0) {
				LogMessage.error("Wrong Item Code:Lana");
				continue;
			}
			if (randomItem != null) {
				categoryModel.setImageResource("/woo/files/" + randomItem.getId());
			}
			categoryModels.add(categoryModel);
		}
		return categoryModels;
	}

}
