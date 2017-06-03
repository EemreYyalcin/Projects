package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.woo.core.generate.GenerateItem;
import com.woo.core.generate.GenerateQuestion;
import com.woo.service.impl.CategoryServiceImpl;
import com.woo.service.impl.FileSystemStorageServiceImpl;
import com.woo.service.impl.ItemServiceImpl;
import com.woo.service.impl.QuestionScoreServiceImpl;
import com.woo.service.impl.QuestionServiceImpl;

@Controller
public class GenerateController {

	@Autowired
	public GenerateController(CategoryServiceImpl categoryService, FileSystemStorageServiceImpl fileService, ItemServiceImpl itemService, QuestionServiceImpl questionService, QuestionScoreServiceImpl questionScoreService) {
		GenerateItem.loadFromFileToDB(fileService, categoryService, itemService);
		GenerateQuestion.createQuestion(categoryService, itemService, questionScoreService, questionService);

	}

}
