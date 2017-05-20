package com.woo.utils;

import java.util.ArrayList;
import java.util.Iterator;

import com.woo.core.attributes.ServiceElement;
import com.woo.core.map.PointMap;
import com.woo.core.map.Side;
import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.domain.Question;
import com.woo.domain.QuestionScore;

public class GenerateQuestion {

	public static void createQuestion(ServiceElement services) {

		Iterable<Category> categoryList = services.getCategoryService().getCategories();
		if (categoryList == null || !categoryList.iterator().hasNext()) {
			LogMessage.logx("Category is Empty Can not create Question");
			return;
		}
		Iterator<Category> iterator = categoryList.iterator();
		while (iterator.hasNext()) {
			Category category = (Category) iterator.next();
			Side side = Side.getSide(category.getMapCountItem());
			int mapQuestion = category.getMapCountQuestion();
			int mapItem = category.getMapCountItem();
			for (int i = mapQuestion; i < mapItem; i++) {
				ArrayList<Integer> neighborList = PointMap.getNeighbor(side, i);
				Item itemA = services.getItemService().getItemByCategoryAndMapCount(category, i);
				if (itemA == null) {
					LogMessage.logx("List Ending 1 MapCount:" + i);
					return;
				}

				for (int j = 0; j < neighborList.size(); j++) {

					Item itemB = services.getItemService().getItemByCategoryAndMapCount(category, neighborList.get(j));
					if (itemB == null) {
						LogMessage.logx("ItemB is null ");
						continue;
					}
					Question question = new Question();
					QuestionScore score = new QuestionScore(0, 0, null);
					services.getQuestionScoreService().addQuestionScore(score);
					question.setCategory(category);
					if (j % 2 == 0) {
						question.setItemA(itemB);
						question.setItemB(itemA);
					} else {
						question.setItemA(itemA);
						question.setItemB(itemB);
					}
					question.setLevel(Level.getLevel(itemA.getYear() - itemB.getYear()));
					question.setScore(score);
					services.getQuestionService().addQuestion(question);
				}
				category.setMapCountQuestion(category.getMapCountQuestion() + 1);
				services.getCategoryService().addCategory(category);

			}

		}

	}

}
