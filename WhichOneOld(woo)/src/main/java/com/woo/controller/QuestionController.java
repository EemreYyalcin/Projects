package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woo.controller.functions.QuestionFunctions;
import com.woo.core.attributes.Codes;
import com.woo.core.attributes.Link;
import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.domain.Question;
import com.woo.ejb.UserProperties;
import com.woo.model.AnswerModel;
import com.woo.model.CategoryScoreModel;
import com.woo.model.ProfileModel;
import com.woo.model.QuestionModel;
import com.woo.service.impl.CategoryScoreServiceImpl;
import com.woo.service.impl.QuestionScoreServiceImpl;
import com.woo.service.impl.QuestionServiceImpl;
import com.woo.utils.generater.GenerateRandom;
import com.woo.utils.log.LogMessage;

@Controller
public class QuestionController {

	private QuestionServiceImpl questionService;

	private QuestionScoreServiceImpl questionScoreService;

	private UserProperties userProperties;

	private CategoryScoreServiceImpl categoryScoreService;

	@Autowired
	public QuestionController(QuestionServiceImpl questionService, QuestionScoreServiceImpl questionScoreService, UserProperties userProperties, CategoryScoreServiceImpl categoryScoreService) {
		this.questionService = questionService;
		this.questionScoreService = questionScoreService;
		this.userProperties = userProperties;
		this.categoryScoreService = categoryScoreService;
	}

	@RequestMapping(value = "/woo/question/random/{token}/{categoryId}/{level}", method = RequestMethod.GET)
	public String getQuestionRandom(@PathVariable("categoryId") Category category, @PathVariable("level") int level, @PathVariable("token") String token, Model view) {
		if (category == null || userProperties.getToken() == null || !userProperties.getToken().equals(token) || level <= 0 || level > 5) {
			return "redirect:" + Link.categoryNames;
		}

		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		Question question = questionService.getRandomQuestion(category, level);
		if (question == null) {
			return "errorpagenoquestion";
		}
		QuestionModel questionModel = QuestionModel.getQuestionModel(question);
		questionModel.setItemAClickNext(Link.randomClickAs + tokenNew + "/" + questionModel.getQuestionId() + "/" + category.getId() + "/" + level);
		questionModel.setItemBClickNext(Link.randomClickBs + tokenNew + "/" + questionModel.getQuestionId() + "/" + category.getId() + "/" + level);
		if (level == 5) {
			questionModel.setItemCClickNext(Link.randomClickCs + tokenNew + "/" + questionModel.getQuestionId() + "/" + category.getId() + "/" + level);
		}
		questionScoreService.setPercentageValue(questionModel, question);
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		view.addAttribute("questionModel", questionModel);
		view.addAttribute("answerModel", answerModel);
		view.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		return "question";
	}

	@RequestMapping(value = "/woo/question/random/clickA/{token}/{questionId}/{categoryId}/{level}", method = RequestMethod.GET)
	public String clickRandomItemA(@PathVariable("questionId") Question question, Model model, @PathVariable("categoryId") Category category, @PathVariable("level") int level, @PathVariable("token") String token) throws InterruptedException {
		if (category == null || userProperties.getToken() == null || !userProperties.getToken().equals(token) || level <= 0 || level > 5) {
			return "redirect:" + Link.categoryNames;
		}
		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		LogMessage.logx("A Clicked");
		String nextQuestion = "redirect:" + Link.randomQuestions + tokenNew + "/" + category.getId() + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		if (answerModel.isAnswerA()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getId() != Codes.errorIntCode) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), true);
			}
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getId() != Codes.errorIntCode) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), false);
		}
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		return nextQuestion;
	}

	@RequestMapping(value = "/woo/question/random/clickB/{token}/{questionId}/{categoryId}/{level}", method = RequestMethod.GET)
	public String clickRandomItemB(@PathVariable("questionId") Question question, Model model, @PathVariable("categoryId") Category category, @PathVariable("level") int level, @PathVariable("token") String token) throws InterruptedException {
		if (category == null || userProperties.getToken() == null || !userProperties.getToken().equals(token) || level <= 0 || level > 5) {
			return "redirect:" + Link.categoryNames;
		}

		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		LogMessage.logx("B Clicked");
		String nextQuestion = "redirect:" + Link.randomQuestions + tokenNew + "/" + category.getId() + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		if (answerModel.isAnswerB()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getId() != Codes.errorIntCode) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), true);
			}
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getId() != Codes.errorIntCode) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), false);
		}
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		return nextQuestion;
	}

	@RequestMapping(value = "/woo/question/random/clickC/{token}/{questionId}/{categoryId}/{level}", method = RequestMethod.GET)
	public String clickRandomItemC(@PathVariable("questionId") Question question, Model model, @PathVariable("categoryId") Category category, @PathVariable("level") int level, @PathVariable("token") String token) throws InterruptedException {
		if (category == null || userProperties.getToken() == null || !userProperties.getToken().equals(token) || level <= 0 || level > 5) {
			return "redirect:" + Link.categoryNames;
		}

		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		LogMessage.logx("C Clicked");
		String nextQuestion = "redirect:" + Link.randomQuestions + tokenNew + "/" + category.getId() + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}
		if (answerModel.isAnswerC()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getId() != Codes.errorIntCode) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), true);
			}
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getId() != Codes.errorIntCode) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), false);
		}
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		return nextQuestion;
	}

	@RequestMapping(value = "/woo/question/getlevel/{token}/{categoryId}", method = RequestMethod.GET)
	public ModelAndView getLevels(@PathVariable("categoryId") Category category, @PathVariable("token") String token) {

		if (category == null || userProperties.getToken() == null) {
			return new ModelAndView("redirect:" + Link.categoryNames);
		}

		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		CategoryScore categoryScore = categoryScoreService.getCategoryScore(userProperties.getId(), category);
		ModelAndView view = null;

		CategoryScoreModel categoryScoreModel;
		if (categoryScore == null) {
			categoryScoreModel = CategoryScoreModel.getEmptyCategoryScoreModel(0);
		}
		else {
			categoryScoreModel = CategoryScoreModel.getCategoryScoreModel(categoryScore, questionService.getQuestionCountByCategory(categoryScore.getCategory()));
		}

		if (userProperties.getId() == Codes.errorIntCode) {
			view = new ModelAndView("selectLevel", "levels", QuestionFunctions.getLevels(Link.randomQuestions + tokenNew + "/" + category.getId(), categoryScoreModel, questionService, true, category));
		}
		else {
			view = new ModelAndView("selectLevel", "levels", QuestionFunctions.getLevels(Link.serialQuestions + tokenNew + "/" + category.getId(), categoryScoreModel, questionService, false, category));
		}
		view.addObject("profile", ProfileModel.getBasicProfileModel(userProperties));
		return view;
	}

	@RequestMapping(value = "/woo/question/{token}/{categoryId}/{pageId}/{level}", method = RequestMethod.GET)
	public String getQuestionSerial(@PathVariable("categoryId") Category category, @PathVariable("pageId") int pageId, @PathVariable("level") int level, @PathVariable("token") String token, Model model) {
		if (category == null || userProperties.getToken() == null || level <= 0 || level > 5 || !userProperties.getToken().equals(token)) {
			return "errorpagenoquestion";
		}

		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		Question question = questionService.getQuestionByCategoryAndLevelWithPage(category, level, new PageRequest(pageId, 1));
		if (question == null) {
			return "redirect:" + Link.categories + category.getId();
		}
		pageId++;
		QuestionModel questionModel = QuestionModel.getQuestionModel(question);
		questionModel.setItemAClickNext(Link.serialClickAs + tokenNew + "/" + questionModel.getQuestionId() + "/" + category.getId() + "/" + level + "/" + pageId);
		questionModel.setItemBClickNext(Link.serialClickBs + tokenNew + "/" + questionModel.getQuestionId() + "/" + category.getId() + "/" + level + "/" + pageId);
		if (level == 5) {
			questionModel.setItemCClickNext(Link.serialClickCs + tokenNew + "/" + questionModel.getQuestionId() + "/" + category.getId() + "/" + level + "/" + pageId);
		}
		questionScoreService.setPercentageValue(questionModel, question);
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		model.addAttribute("questionModel", questionModel);
		model.addAttribute("answerModel", answerModel);
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		return "question";
	}

	@RequestMapping(value = "/woo/question/clickA/{token}/{questionId}/{categoryId}/{level}/{pageId}", method = RequestMethod.GET)
	public String clickItemA(@PathVariable("questionId") Question question, Model model, @PathVariable("categoryId") Category category, @PathVariable("level") int level, @PathVariable("token") String token, @PathVariable("pageId") int pageId) throws InterruptedException {
		LogMessage.logx("A Clicked");

		if (category == null || userProperties.getToken() == null || level <= 0 || level > 5 || !userProperties.getToken().equals(token)) {
			return "errorpagenoquestion";
		}

		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		String nextQuestion = "redirect:" + Link.serialQuestions + tokenNew + "/" + category.getId() + "/" + pageId + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}
		if (answerModel.isAnswerA()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getId() != Codes.errorIntCode) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), true);
			}
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getId() != Codes.errorIntCode) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), false);
		}
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		return nextQuestion;
	}

	@RequestMapping(value = "/woo/question/clickB/{token}/{questionId}/{categoryId}/{level}/{pageId}", method = RequestMethod.GET)
	public String clickItemB(@PathVariable("questionId") Question question, Model model, @PathVariable("categoryId") Category category, @PathVariable("level") int level, @PathVariable("token") String token, @PathVariable("pageId") int pageId) throws InterruptedException {
		LogMessage.logx("B Clicked");

		if (category == null || userProperties.getToken() == null || level <= 0 || level > 5 || !userProperties.getToken().equals(token)) {
			return "errorpagenoquestion";
		}

		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		String nextQuestion = "redirect:" + Link.serialQuestions + tokenNew + "/" + category.getId() + "/" + pageId + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		if (answerModel.isAnswerB()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getId() != Codes.errorIntCode) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), true);
			}
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getId() != Codes.errorIntCode) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), false);
		}
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		return nextQuestion;
	}

	@RequestMapping(value = "/woo/question/clickC/{token}/{questionId}/{categoryId}/{level}/{pageId}", method = RequestMethod.GET)
	public String clickItemC(@PathVariable("questionId") Question question, Model model, @PathVariable("categoryId") Category category, @PathVariable("level") int level, @PathVariable("token") String token, @PathVariable("pageId") int pageId) throws InterruptedException {
		LogMessage.logx("C Clicked");
		if (category == null || userProperties.getToken() == null || level <= 0 || level > 5 || !userProperties.getToken().equals(token)) {
			return "errorpagenoquestion";
		}

		String tokenNew = GenerateRandom.generateToken();
		userProperties.setToken(tokenNew);

		String nextQuestion = "redirect:" + Link.serialQuestions + tokenNew + "/" + category.getId() + "/" + pageId + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}
		if (answerModel.isAnswerC()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getId() != Codes.errorIntCode) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), true);
			}
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getId() != Codes.errorIntCode) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getId(), false);
		}
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		return nextQuestion;
	}

}
