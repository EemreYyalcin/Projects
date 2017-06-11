package com.woo.controller.functions;

import java.util.ArrayList;

import com.woo.core.attributes.Link;
import com.woo.domain.CategoryScore;
import com.woo.domain.Item;
import com.woo.domain.Question;
import com.woo.model.AnswerModel;
import com.woo.model.CategoryScoreModel;
import com.woo.model.LevelModel;
import com.woo.model.RatioModel;
import com.woo.service.impl.ItemServiceImpl;
import com.woo.service.impl.QuestionServiceImpl;
import com.woo.utils.calculation.Calculator;
import com.woo.utils.log.LogMessage;

public class QuestionFunctions {

	public static ArrayList<LevelModel> getLevels(ItemServiceImpl itemService, String nextUrl, CategoryScore categoryScore, QuestionServiceImpl questionService, boolean isRandom) {

		CategoryScoreModel categoryScoreModel;
		if (categoryScore == null) {
			categoryScoreModel = CategoryScoreModel.getEmptyCategoryScoreModel(0);
		}
		else {
			categoryScoreModel = CategoryScoreModel.getCategoryScoreModel(categoryScore, questionService.getQuestionCountByCategory(categoryScore.getCategory()));
		}

		String[] names = { "VERY EASY", "EASY", "MEDIUM", "HARD", "VERY HARD" };
		ArrayList<LevelModel> list = new ArrayList<>();
		Item item;
		String suffix = "1";

		for (int i = 0; i < names.length; i++) {
			RatioModel ratioModel = null;
			int pageId = 0;
			int status = 0;
			int totalQuestion = 0;
			item = itemService.getItemByFilename(names[i]);
			if (item == null || item.getId() == 0) {
				suffix = "1";
			}
			else {
				suffix = item.getId() + "";
			}
			switch (i) {
			case 0:
				ratioModel = categoryScoreModel.getScoreModel().getVeryEasyRatio();
				pageId = categoryScoreModel.getVeryEasyPageId();
				break;
			case 1:
				ratioModel = categoryScoreModel.getScoreModel().getEasyRatio();
				pageId = categoryScoreModel.getEasyPageId();
				break;
			case 2:
				ratioModel = categoryScoreModel.getScoreModel().getMediumRatio();
				pageId = categoryScoreModel.getMediumPageId();
				break;
			case 3:
				ratioModel = categoryScoreModel.getScoreModel().getHardRatio();
				pageId = categoryScoreModel.getHardPageId();
				break;
			case 4:
				ratioModel = categoryScoreModel.getScoreModel().getVeryHardRatio();
				pageId = categoryScoreModel.getVeryHardPageId();
			}
			if (categoryScore == null) {
				totalQuestion = 0;
			}
			else {
				totalQuestion = questionService.getQuestionCountByCategoryAndLevel(categoryScore.getCategory(), i + 1);
			}
			status = (int) Calculator.getPercentage(pageId, totalQuestion);
			if (isRandom) {
				list.add(new LevelModel(names[i], nextUrl + "/" + (i + 1), "/images/Capture" + (i + 1) + ".jpg", ratioModel, status));
			}
			else {
				list.add(new LevelModel(names[i], nextUrl + "/" + pageId + "/" + (i + 1), Link.imageSources + suffix, ratioModel, status));
			}
		}
		return list;
	}

	public static AnswerModel answerQuestion(Question question) {
		if (question == null || question.getId() == 0) {
			LogMessage.error("Get questionModel Error. Code:Restart");
			return null;
		}
		int option = -1;
		try {
			if (question.getItemA().getYear() < question.getItemB().getYear()) {
				option = 1;
			}
			else if (question.getItemA().getYear() > question.getItemB().getYear()) {
				option = 2;
			}
			else {
				option = 3;
			}
		}
		catch (Exception e) {
			LogMessage.error("Answer Question Error. Code:WARN");
			e.printStackTrace();
		}

		if (option == -1) {
			LogMessage.error("Get questionModel Error. Code:Option");
			return null;
		}
		AnswerModel answerModel = new AnswerModel();
		if (option == 1) {
			answerModel.setAnswerA();
		}
		else if (option == 2) {
			answerModel.setAnswerB();
		}
		else {
			answerModel.setAnswerC();
		}

		return answerModel;
	}

}
