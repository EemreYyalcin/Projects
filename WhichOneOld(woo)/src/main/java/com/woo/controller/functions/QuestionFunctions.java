package com.woo.controller.functions;

import java.util.ArrayList;

import com.woo.core.attributes.Link;
import com.woo.domain.Category;
import com.woo.domain.CategoryScore;
import com.woo.domain.Item;
import com.woo.domain.Question;
import com.woo.domain.QuestionScore;
import com.woo.model.AnswerModel;
import com.woo.model.CategoryScoreModel;
import com.woo.model.LevelModel;
import com.woo.model.QuestionModel;
import com.woo.model.RatioModel;
import com.woo.service.impl.ItemServiceImpl;
import com.woo.service.impl.QuestionScoreServiceImpl;
import com.woo.service.impl.QuestionServiceImpl;
import com.woo.utils.generater.GenerateRandom;
import com.woo.utils.log.LogMessage;

public class QuestionFunctions {

	public static Question getRandomQuestion(QuestionServiceImpl questionService, Category category, int level) {
		ArrayList<Question> questions = questionService.getQuestionByCategoryAndLevel(category, level);
		if (questions.size() <= 0) {
			LogMessage.error("No Questions!! Code:Accessor");
			return null;
		}
		int randomValue = GenerateRandom.getRandomInt(0, questions.size());
		return questions.get(randomValue);
	}

	public static ArrayList<LevelModel> getLevels(ItemServiceImpl itemService, String nextUrl, CategoryScore categoryScore, boolean isRandom) {

		CategoryScoreModel categoryScoreModel;
		if (categoryScore == null) {
			categoryScoreModel = CategoryScoreModel.getEmptyCategoryScoreModel(0);
		}
		else {
			categoryScoreModel = CategoryScoreModel.getCategoryScoreModel(categoryScore);
		}

		String[] names = { "VERY EASY", "EASY", "MEDIUM", "HARD", "VERY HARD" };
		ArrayList<LevelModel> list = new ArrayList<>();
		Item item;
		String suffix = "1";

		for (int i = 0; i < names.length; i++) {
			RatioModel ratioModel = null;
			int pageId = 0;
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
				pageId = categoryScore.getVeryEasyPageId();
				break;
			case 1:
				ratioModel = categoryScoreModel.getScoreModel().getEasyRatio();
				pageId = categoryScore.getEasyPageId();
				break;
			case 2:
				ratioModel = categoryScoreModel.getScoreModel().getMediumRatio();
				pageId = categoryScore.getMediumPageId();
				break;
			case 3:
				ratioModel = categoryScoreModel.getScoreModel().getHardRatio();
				pageId = categoryScore.getHardPageId();
				break;
			case 4:
				ratioModel = categoryScoreModel.getScoreModel().getVeryHardRatio();
				pageId = categoryScore.getVeryHardPageId();
				break;
			}
			if (isRandom) {
				list.add(new LevelModel(names[i], nextUrl + "/" + (i + 1), Link.imageSource + suffix, ratioModel));								
			}else{
				list.add(new LevelModel(names[i], nextUrl + "/" + pageId + "/" + (i + 1), Link.imageSource + suffix, ratioModel));				
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

	public static void setPercentageValue(QuestionModel questionModel, Question question, QuestionScoreServiceImpl questionScoreService) {
		if (question.getScore() == null) {
			questionModel.setTotalPercentage(0);
			questionModel.setFalsePercentage(100);
			questionModel.setTruePercentage(0);
			return;
		}
		int trueCount = question.getScore().getTrueCount();
		int falseCount = question.getScore().getFalseCount();
		int totalCount = trueCount + falseCount;
		if (totalCount == 0) {
			trueCount = GenerateRandom.getRandomInt(1, 7);
			falseCount = GenerateRandom.getRandomInt(1, 7);
			totalCount = trueCount + falseCount;
			questionScoreService.addQuestionScore(new QuestionScore(trueCount, falseCount, question));
		}
		float truePercentage = ((float) trueCount / totalCount) * 100;
		int falsePercentage = 100 - (int) truePercentage;
		questionModel.setFalsePercentage(falsePercentage);
		questionModel.setTruePercentage((int) truePercentage);
		float totalPercentage = 0;
		if (totalCount < 100) {
			totalPercentage = totalCount;
		}
		else if (totalCount < 500) {
			totalPercentage = ((float) ((float) totalCount / (500))) * 100;
		}
		else if (totalCount < 1000) {
			totalPercentage = ((float) ((float) totalCount / (1000))) * 100;
		}
		else if (totalCount < 10000) {
			totalPercentage = ((float) ((float) totalCount / (10000))) * 100;
		}
		questionModel.setTotalPercentage((int) totalPercentage);
	}

}
