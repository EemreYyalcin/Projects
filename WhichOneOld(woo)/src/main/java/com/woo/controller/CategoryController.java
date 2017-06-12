package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woo.core.attributes.Codes;
import com.woo.domain.Category;
import com.woo.ejb.UserProperties;
import com.woo.model.ProfileModel;
import com.woo.service.impl.CategoryServiceImpl;
import com.woo.service.impl.StatisticServiceImpl;
import com.woo.utils.log.LogMessage;

@Controller
@RequestMapping("/woo/category")
public class CategoryController {

	private CategoryServiceImpl categoryService;

	private UserProperties userProperties;

	private StatisticServiceImpl statisticService;

	@Autowired
	public CategoryController(CategoryServiceImpl categoryService, UserProperties userProperties, StatisticServiceImpl statisticService) {
		this.categoryService = categoryService;
		this.userProperties = userProperties;
		this.statisticService = statisticService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getCategoryNamesAndImages() {
		ModelAndView view = new ModelAndView("categoryNames", "categoryNames", categoryService.getCategoriesWithName(statisticService.getStatisticByUserId(userProperties.getId()), userProperties.getId(), true));
		view.addObject("profile", ProfileModel.getBasicProfileModel(userProperties));
		return view;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getCategoryDecadesAndImages(@PathVariable("id") Category category) {
		if (category == null) {
			LogMessage.error("Temporarly category is null! Code:Inna");
			return null;
		}
		LogMessage.logx("categoryçgetname " + category.getName() + " " + category.getId());
		ModelAndView view = new ModelAndView("categoryDecadesAndImages", "categoryDecades", categoryService.getCategoriesByDecade(category.getName(), userProperties.getId()));
		view.addObject("profile", ProfileModel.getBasicProfileModel(userProperties));
		return view;
	}

	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public String getMyCategoryNamesAndImages(Model model) {
		if (userProperties.getId() == Codes.errorIntCode) {
			return "redirect:/woo/login";
		}
		model.addAttribute("categoryNames", categoryService.getCategoriesWithName(statisticService.getStatisticByUserId(userProperties.getId()), userProperties.getId(), false));
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		model.addAttribute("myCategory", "myCategory");
		return "categoryNames";

	}

}
