package com.woo.core.attributes;

import com.woo.service.CategoryServiceImpl;
import com.woo.service.FileSystemStorageService;
import com.woo.service.ItemServiceImpl;
import com.woo.service.QuestionScoreServiceImpl;
import com.woo.service.QuestionServiceImpl;

public class ServiceElement {

	private CategoryServiceImpl categoryService;
	private ItemServiceImpl itemService;
	private QuestionServiceImpl questionService;
	private QuestionScoreServiceImpl questionScoreService;
	private FileSystemStorageService fileService;

	public CategoryServiceImpl getCategoryService() {
		return categoryService;
	}

	public ServiceElement setCategoryService(CategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
		return this;
	}

	public ItemServiceImpl getItemService() {
		return itemService;
	}

	public ServiceElement setItemService(ItemServiceImpl itemService) {
		this.itemService = itemService;
		return this;
	}

	public QuestionServiceImpl getQuestionService() {
		return questionService;
	}

	public ServiceElement setQuestionService(QuestionServiceImpl questionService) {
		this.questionService = questionService;
		return this;
	}

	public QuestionScoreServiceImpl getQuestionScoreService() {
		return questionScoreService;
	}

	public ServiceElement setQuestionScoreService(QuestionScoreServiceImpl questionScoreService) {
		this.questionScoreService = questionScoreService;
		return this;
	}

	public FileSystemStorageService getFileService() {
		return fileService;
	}

	public ServiceElement setFileService(FileSystemStorageService fileService) {
		this.fileService = fileService;
		return this;
	}

}
