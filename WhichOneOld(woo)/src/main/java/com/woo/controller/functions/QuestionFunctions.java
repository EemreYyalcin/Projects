package com.woo.controller.functions;

import java.util.ArrayList;

import com.woo.core.attributes.Link;
import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.domain.Question;
import com.woo.domain.QuestionScore;
import com.woo.model.AnswerModel;
import com.woo.model.LevelModel;
import com.woo.model.QuestionModel;
import com.woo.service.ItemServiceImpl;
import com.woo.service.QuestionScoreServiceImpl;
import com.woo.service.QuestionServiceImpl;
import com.woo.utils.LogMessage;
import com.woo.utils.Utils;

public class QuestionFunctions {

	public static Question getRandomQuestion(QuestionServiceImpl questionService, Category category, int level) {
		ArrayList<Question> questions = questionService.getQuestionByCategoryAndLevel(category, level);
		if (questions.size() <= 0) {
			LogMessage.error("No Questions!! Code:Accessor");
			return null;
		}
		int randomValue = Utils.getRandomInt(0, questions.size());
		return questions.get(randomValue);
	}

	public static ArrayList<LevelModel> getLevels(ItemServiceImpl itemService, String nextUrl) {

		String[] names = { "VERY EASY", "EASY", "NORMAL", "HARD", "VERY HARD" };
		ArrayList<LevelModel> list = new ArrayList<>();
		Item item;
		String suffix = "1";
		for (int i = 0; i < names.length; i++) {
			item = itemService.getItemByFilename(names[i]);
			if (item == null || item.getId() == 0) {
				suffix = "1";
			} else {
				suffix = item.getId() + "";
			}
			list.add(new LevelModel(names[i], i + 1, nextUrl, Link.imageSource + suffix));
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
			} else if (question.getItemA().getYear() > question.getItemB().getYear()) {
				option = 2;
			} else {
				option = 3;
			}
		} catch (Exception e) {
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
		} else if (option == 2) {
			answerModel.setAnswerB();
		} else {
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
			trueCount = Utils.getRandomInt(1, 7);
			falseCount = Utils.getRandomInt(1, 7);
			totalCount = trueCount + falseCount;
			questionScoreService.addQuestionScore(new QuestionScore(trueCount, falseCount, question));
		}
		float truePercentage = ((float)trueCount / totalCount) * 100;
		int falsePercentage = 100 - (int)truePercentage;
		questionModel.setFalsePercentage(falsePercentage);
		questionModel.setTruePercentage((int)truePercentage);
		float totalPercentage = 0;
		if (totalCount < 100) {
			totalPercentage = totalCount;
		}else if (totalCount < 500) {
			totalPercentage = ((float)((float)totalCount / (500))) * 100;
		}else if (totalCount < 1000) {
			totalPercentage = ((float)((float)totalCount / (1000))) * 100;			
		}else if (totalCount < 10000) {
			totalPercentage = ((float)((float)totalCount / (10000))) * 100;
		}
		questionModel.setTotalPercentage((int)totalPercentage);
	}

}
