package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.woo.controller.functions.CategoryFunctions;
import com.woo.domain.Category;
import com.woo.service.CategoryServiceImpl;
import com.woo.service.ItemServiceImpl;
import com.woo.utils.LogMessage;

@Controller
public class CategoryController {

	private CategoryServiceImpl categoryService;
	private ItemServiceImpl itemService;

	@Autowired
	public CategoryController(CategoryServiceImpl categoryServiceImpl, ItemServiceImpl itemServiceImpl) {
		this.categoryService = categoryServiceImpl;
		this.itemService = itemServiceImpl;
	}

	@GetMapping("/woo/categories")
	public ModelAndView getCategoryNamesAndImages() {
		ModelAndView view = new ModelAndView("categoryNames", "categoryNames", CategoryFunctions.getCategoriesByName(categoryService.getCategories(), itemService, categoryService));
		return view;
	}

	@GetMapping("/woo/category/{id}")
	public ModelAndView getCategoryDecadesAndImages(@PathVariable("id") Category category) {
		if (category == null) {
			LogMessage.error("Temporarly category is null! Code:Inna");
			return null;
		}
		LogMessage.logx("categoryçgetname " + category.getName() + " " + category.getId());
		ModelAndView view = new ModelAndView("categoryDecadesAndImages", "categoryDecades", CategoryFunctions.getCategoriesByDecade(categoryService.getCategoriesByName(category.getName()), itemService));
		return view;
	}

}
