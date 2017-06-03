package com.woo.core.generate;

import java.util.ArrayList;
import java.util.Iterator;

import com.woo.core.map.PointMap;
import com.woo.core.map.Side;
import com.woo.core.stage.Level;
import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.domain.Question;
import com.woo.domain.QuestionScore;
import com.woo.service.impl.CategoryServiceImpl;
import com.woo.service.impl.ItemServiceImpl;
import com.woo.service.impl.QuestionScoreServiceImpl;
import com.woo.service.impl.QuestionServiceImpl;
import com.woo.utils.log.LogMessage;

public class GenerateQuestion {

	public static void createQuestion(CategoryServiceImpl categoryService, ItemServiceImpl itemService, QuestionScoreServiceImpl questionScoreService, QuestionServiceImpl questionService) {

		Iterable<Category> categoryList = categoryService.getCategories();
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
				Item itemA = itemService.getItemByCategoryAndMapCount(category, i);
				if (itemA == null) {
					LogMessage.logx("List Ending 1 MapCount:" + i);
					return;
				}

				for (int j = 0; j < neighborList.size(); j++) {

					Item itemB = itemService.getItemByCategoryAndMapCount(category, neighborList.get(j));
					if (itemB == null) {
						LogMessage.logx("ItemB is null ");
						continue;
					}
					Question question = new Question();
					QuestionScore score = new QuestionScore(0, 0, null);
					questionScoreService.addQuestionScore(score);
					question.setCategory(category);
					if (j % 2 == 0) {
						question.setItemA(itemB);
						question.setItemB(itemA);
					}
					else {
						question.setItemA(itemA);
						question.setItemB(itemB);
					}
					question.setLevel(Level.getLevel(itemA.getYear() - itemB.getYear()));
					question.setScore(score);
					questionService.addQuestion(question);
				}
				category.setMapCountQuestion(category.getMapCountQuestion() + 1);
				categoryService.addCategory(category);

			}

		}

	}

}
