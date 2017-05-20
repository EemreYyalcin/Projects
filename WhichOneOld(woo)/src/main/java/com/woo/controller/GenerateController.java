package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.woo.core.attributes.ServiceElement;
import com.woo.service.CategoryServiceImpl;
import com.woo.service.FileSystemStorageService;
import com.woo.service.ItemServiceImpl;
import com.woo.service.QuestionScoreServiceImpl;
import com.woo.service.QuestionServiceImpl;
import com.woo.utils.GenerateItem;
import com.woo.utils.GenerateQuestion;
import com.woo.utils.LogMessage;

@Controller
public class GenerateController {

	private ServiceElement services = new ServiceElement();

	@Autowired
	public GenerateController(CategoryServiceImpl categoryService, FileSystemStorageService fileService, ItemServiceImpl itemService, QuestionServiceImpl questionService, QuestionScoreServiceImpl questionScoreService) {
		services.setCategoryService(categoryService).setFileService(fileService).setItemService(itemService).setQuestionService(questionService).setQuestionScoreService(questionScoreService);
		GenerateItem.loadFromFileToDB(fileService, categoryService, itemService);
		LogMessage.logx("Categories:");
//		categoryService.getCategories();
		GenerateQuestion.createQuestion(services);

	}

}
