package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woo.core.attributes.Codes;
import com.woo.domain.Statistic;
import com.woo.ejb.UserProperties;
import com.woo.model.ProfileModel;
import com.woo.model.RatioModel;
import com.woo.model.ScoreModel;
import com.woo.service.impl.CategoryScoreServiceImpl;
import com.woo.service.impl.StatisticServiceImpl;

@Controller
@RequestMapping("/woo/profile")
public class ProfileController {

	private UserProperties userProperties;

	private StatisticServiceImpl statisticService;

	private CategoryScoreServiceImpl categoryScoreService;

	@Autowired
	public ProfileController(UserProperties userProperties, StatisticServiceImpl statisticService, CategoryScoreServiceImpl categoryScoreService) {
		this.userProperties = userProperties;
		this.statisticService = statisticService;
		this.categoryScoreService = categoryScoreService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getProfilePage() {
		ModelAndView view = new ModelAndView("profilePage");
		Statistic statistic = null;
		ProfileModel profileModel = ProfileModel.getBasicProfileModel(userProperties);
		if (userProperties.getId() != Codes.errorIntCode) {
			statistic = statisticService.getStatisticByUserId(userProperties.getId());
		}
		profileModel.setCategoryScoreModel(categoryScoreService.getTotalScoreProfile(statistic));
		ScoreModel scoreModel = profileModel.getCategoryScoreModel().getScoreModel();
		profileModel.setVeryHardClickPercentage(RatioModel.setTotalClickPercentage(scoreModel.getVeryHardRatio()));
		profileModel.setHardClickPercentage(RatioModel.setTotalClickPercentage(scoreModel.getHardRatio()));
		profileModel.setMediumClickPercentage(RatioModel.setTotalClickPercentage(scoreModel.getMediumRatio()));
		profileModel.setEasyClickPercentage(RatioModel.setTotalClickPercentage(scoreModel.getEasyRatio()));
		profileModel.setVeryEasyClickPercentage(RatioModel.setTotalClickPercentage(scoreModel.getVeryEasyRatio()));
		view.addObject("profile", profileModel);
		return view;
	}

}
