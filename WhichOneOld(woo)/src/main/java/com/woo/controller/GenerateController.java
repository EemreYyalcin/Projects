package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.woo.service.impl.CategoryServiceImpl;
import com.woo.service.impl.FileSystemStorageServiceImpl;
import com.woo.service.impl.ItemServiceImpl;
import com.woo.service.impl.QuestionScoreServiceImpl;
import com.woo.service.impl.QuestionServiceImpl;
import com.woo.utils.GenerateItem;
import com.woo.utils.GenerateQuestion;

@Controller
public class GenerateController {

	@Autowired
	private CategoryServiceImpl categoryService;
	@Autowired
	private FileSystemStorageServiceImpl fileService;
	@Autowired
	private ItemServiceImpl itemService;
	@Autowired
	private QuestionServiceImpl questionService;
	@Autowired
	private QuestionScoreServiceImpl questionScoreService;

	@Autowired
	public GenerateController() {
		GenerateItem.loadFromFileToDB(fileService, categoryService, itemService);
		GenerateQuestion.createQuestion(categoryService, itemService, questionScoreService, questionService);

	}

}
