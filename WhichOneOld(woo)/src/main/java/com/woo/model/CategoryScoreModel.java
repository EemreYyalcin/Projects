package com.woo.model;

import com.woo.domain.CategoryScore;

public class CategoryScoreModel {

	private ScoreModel scoreModel;

	private int status;

	private int totalQuestionCount;

	public ScoreModel getScoreModel() {
		return scoreModel;
	}

	public void setScoreModel(ScoreModel scoreModel) {
		this.scoreModel = scoreModel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotalQuestionCount() {
		return totalQuestionCount;
	}

	public void setTotalQuestionCount(int totalQuestionCount) {
		this.totalQuestionCount = totalQuestionCount;
	}

	public CategoryScoreModel addCategoryScoreModel(CategoryScoreModel categoryScoreModel) {
		CategoryScoreModel tempModel = new CategoryScoreModel();
		tempModel.setScoreModel(getScoreModel().addScoreModel(categoryScoreModel.getScoreModel()));
		int totalQuestionCount = categoryScoreModel.getTotalQuestionCount() + getTotalQuestionCount();
		int totalAnswered = categoryScoreModel.getScoreModel().getTotalCount() + getScoreModel().getTotalCount();
		if (totalQuestionCount <= 0) {
			tempModel.setStatus(0);
		}
		else {
			float percentage = ((totalAnswered * 100) / totalQuestionCount);
			tempModel.setStatus((int) percentage);
		}
		return tempModel;
	}

	public static CategoryScoreModel getCategoryScoreModel(CategoryScore categoryScore) {
		CategoryScoreModel categoryScoreModel = new CategoryScoreModel();
		int totalQuestionCount = 1;
		if (categoryScore == null) {
			return getEmptyCategoryScoreModel(1);
		}else {
			categoryScoreModel.setScoreModel(ScoreModel.getScoreModel(categoryScore.getScore()));
			totalQuestionCount = categoryScore.getCategory().getMapCountQuestion();
			
		}
		int totalAnswered = categoryScoreModel.getScoreModel().getTotalCount();
		categoryScoreModel.setTotalQuestionCount(totalQuestionCount);
		float percentage = ((totalAnswered * 100) / totalQuestionCount);
		categoryScoreModel.setStatus((int) percentage);
		return categoryScoreModel;
	}

	public static CategoryScoreModel getEmptyCategoryScoreModel(int questionCount) {
		CategoryScoreModel categoryScoreModel = new CategoryScoreModel();
		categoryScoreModel.setScoreModel(new ScoreModel(null));
		categoryScoreModel.setStatus(0);
		categoryScoreModel.setTotalQuestionCount(questionCount);
		return categoryScoreModel;
	}

}
