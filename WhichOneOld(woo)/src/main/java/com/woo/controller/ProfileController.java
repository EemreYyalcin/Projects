package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woo.controller.functions.ProfileFuctions;
import com.woo.core.attributes.Link;
import com.woo.domain.Statistic;
import com.woo.ejb.UserProperties;
import com.woo.model.ProfileModel;
import com.woo.model.RatioModel;
import com.woo.model.ScoreModel;
import com.woo.service.impl.StatisticServiceImpl;

@Controller
public class ProfileController {

	@Autowired
	private UserProperties userProperties;

	@Autowired
	private StatisticServiceImpl statisticService;

	@RequestMapping("/woo/profile")
	public ModelAndView getProfilePage() {
		ModelAndView view = new ModelAndView("profilePage");
		Statistic statistic = null;
		ProfileModel profileModel = new ProfileModel();
		profileModel.setHome(Link.home);
		profileModel.setExplore(Link.explore);
		profileModel.setMyCategories(Link.myCategory);
		profileModel.setProfile(Link.profile);
		if (userProperties.getContact() == null) {
			// Empty Model Or Demo Model
			profileModel.setName("Guess");
			profileModel.setSurname("Demo");
		}
		else {
			statistic = statisticService.getStatisticByUserId(userProperties.getContact().getId());
			profileModel.setName(userProperties.getContact().getName());
			profileModel.setSurname(userProperties.getContact().getSurname());
		}
		profileModel.setCategoryScoreModel(ProfileFuctions.getTotalScoreProfile(statistic));
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
