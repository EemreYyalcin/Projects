package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woo.controller.functions.QuestionFunctions;
import com.woo.core.attributes.Link;
import com.woo.domain.Category;
import com.woo.domain.Question;
import com.woo.ejb.UserProperties;
import com.woo.model.AnswerModel;
import com.woo.model.QuestionModel;
import com.woo.service.impl.CategoryScoreServiceImpl;
import com.woo.service.impl.ItemServiceImpl;
import com.woo.service.impl.QuestionScoreServiceImpl;
import com.woo.service.impl.QuestionServiceImpl;
import com.woo.utils.LogMessage;

@Controller
public class QuestionController {

	private QuestionServiceImpl questionService;
	private ItemServiceImpl itemService;
	private QuestionScoreServiceImpl questionScoreService;
	private UserProperties userProperties;
	private CategoryScoreServiceImpl categoryScoreService;

	@Autowired
	public QuestionController(QuestionServiceImpl questionService, ItemServiceImpl itemService,
			QuestionScoreServiceImpl questionScoreService, UserProperties userProperties,
			CategoryScoreServiceImpl categoryScoreService) {
		this.questionService = questionService;
		this.itemService = itemService;
		this.questionScoreService = questionScoreService;
		this.userProperties = userProperties;
		this.categoryScoreService = categoryScoreService;
	}

	@RequestMapping("/woo/questionRandom/{categoryId}/{level}")
	public String getQuestionRandom(@PathVariable("categoryId") Category category, @PathVariable("level") int level,
			Model view) {
		Question question = QuestionFunctions.getRandomQuestion(questionService, category, level);
		if (question == null) {
			return "errorpagenoquestion";
		}
		QuestionModel questionModel = QuestionModel.getQuestionModel(question);
		questionModel.setItemAClickNext(
				"/woo/question/random/clickA/" + questionModel.getQuestionId() + "/" + category.getId() + "/" + level);
		questionModel.setItemBClickNext(
				"/woo/question/random/clickB/" + questionModel.getQuestionId() + "/" + category.getId() + "/" + level);
		if (level == 5) {
			questionModel.setItemCClickNext("/woo/question/random/clickC/" + questionModel.getQuestionId() + "/"
					+ category.getId() + "/" + level);
		}
		QuestionFunctions.setPercentageValue(questionModel, question, questionScoreService);
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		view.addAttribute("questionModel", questionModel);
		view.addAttribute("answerModel", answerModel);
		return "question";
	}

	@RequestMapping("/woo/question/random/clickA/{questionId}/{categoryId}/{level}")
	public String clickItemA(@PathVariable("questionId") Question question, Model model,
			@PathVariable("categoryId") Category category, @PathVariable("level") int level)
			throws InterruptedException {
		LogMessage.logx("A Clicked");
		String nextQuestion = "redirect:/woo/questionRandom/" + category.getId() + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			return nextQuestion;
		}

		if (answerModel.isAnswerA()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getContact() != null) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getContact(), true);
			}
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getContact() != null) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getContact(), false);
		}

		return nextQuestion;
	}

	@RequestMapping("/woo/question/random/clickB/{questionId}/{categoryId}/{level}")
	public String clickItemB(@PathVariable("questionId") Question question, Model model,
			@PathVariable("categoryId") Category category, @PathVariable("level") int level)
			throws InterruptedException {
		LogMessage.logx("B Clicked");
		String nextQuestion = "redirect:/woo/questionRandom/" + category.getId() + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			return nextQuestion;
		}

		if (answerModel.isAnswerB()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getContact() != null) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getContact(), true);
			}
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getContact() != null) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getContact(), false);
		}

		return nextQuestion;
	}

	@RequestMapping("/woo/question/random/clickC/{questionId}/{categoryId}/{level}")
	public String clickItemC(@PathVariable("questionId") Question question, Model model,
			@PathVariable("categoryId") Category category, @PathVariable("level") int level)
			throws InterruptedException {
		LogMessage.logx("C Clicked");
		String nextQuestion = "redirect:/woo/questionRandom/" + category.getId() + "/" + level;
		AnswerModel answerModel = QuestionFunctions.answerQuestion(question);
		if (answerModel == null) {
			return nextQuestion;
		}
		if (answerModel.isAnswerC()) {
			questionScoreService.updateQuestionResult(question, true);
			if (userProperties.getContact() != null) {
				categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getContact(), true);
			}
			return nextQuestion;
		}

		questionScoreService.updateQuestionResult(question, false);
		if (userProperties.getContact() != null) {
			categoryScoreService.updateCategoryScoreTable(category, level, userProperties.getContact(), false);
		}
		return nextQuestion;
	}

	@RequestMapping("/woo/question/getlevel/{categoryId}")
	public ModelAndView getLevels(@PathVariable("categoryId") Long categoryId) {
		ModelAndView view = new ModelAndView("selectLevel", "levels",
				QuestionFunctions.getLevels(itemService, Link.randomQuestion + categoryId + "/"));
		return view;
	}

}
