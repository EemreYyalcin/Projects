package com.woo.model;

import com.woo.domain.CategoryScore;
import com.woo.utils.calculation.Calculator;

public class CategoryScoreModel {

	private ScoreModel scoreModel;

	private int veryEasyPageId = 0;

	private int easyPageId = 0;

	private int mediumPageId = 0;

	private int hardPageId = 0;

	private int veryHardPageId = 0;

	private int status = 0;

	private int questionCount = 0;

	public CategoryScoreModel addCategoryScoreModel(CategoryScoreModel categoryScoreModel, int questionCount) {
		CategoryScoreModel tempModel = new CategoryScoreModel();
		tempModel.setScoreModel(getScoreModel().addScoreModel(categoryScoreModel.getScoreModel()));
		tempModel.setVeryEasyPageId(getVeryEasyPageId() + categoryScoreModel.getVeryEasyPageId());
		tempModel.setEasyPageId(getEasyPageId() + categoryScoreModel.getEasyPageId());
		tempModel.setMediumPageId(getMediumPageId() + categoryScoreModel.getMediumPageId());
		tempModel.setHardPageId(getHardPageId() + categoryScoreModel.getHardPageId());
		tempModel.setVeryHardPageId(getVeryHardPageId() + categoryScoreModel.getVeryHardPageId());
		tempModel.setQuestionCount(getQuestionCount() + questionCount);
		tempModel.setStatus();
		return tempModel;
	}

	public static CategoryScoreModel getCategoryScoreModel(CategoryScore categoryScore, int questionCount) {
		CategoryScoreModel categoryScoreModel = new CategoryScoreModel();
		if (categoryScore == null) {
			return getEmptyCategoryScoreModel(questionCount);
		}
		categoryScoreModel.setScoreModel(ScoreModel.getScoreModel(categoryScore.getScore()));
		categoryScoreModel.setVeryHardPageId(categoryScore.getVeryHardPageId()).setHardPageId(categoryScore.getHardPageId()).setMediumPageId(categoryScore.getMediumPageId()).setEasyPageId(categoryScore.getEasyPageId()).setVeryEasyPageId(categoryScore.getVeryEasyPageId());
		categoryScoreModel.setQuestionCount(questionCount);
		categoryScoreModel.setStatus();
		return categoryScoreModel;
	}

	public static CategoryScoreModel getEmptyCategoryScoreModel(int questionCount) {
		CategoryScoreModel categoryScoreModel = new CategoryScoreModel();
		categoryScoreModel.setScoreModel(new ScoreModel(null));
		return categoryScoreModel;
	}

	public ScoreModel getScoreModel() {
		return scoreModel;
	}

	public CategoryScoreModel setScoreModel(ScoreModel scoreModel) {
		this.scoreModel = scoreModel;
		return this;
	}

	public int getVeryEasyPageId() {
		return veryEasyPageId;
	}

	public CategoryScoreModel setVeryEasyPageId(int veryEasyPageId) {
		this.veryEasyPageId = veryEasyPageId;
		return this;
	}

	public int getEasyPageId() {
		return easyPageId;
	}

	public CategoryScoreModel setEasyPageId(int easyPageId) {
		this.easyPageId = easyPageId;
		return this;
	}

	public int getMediumPageId() {
		return mediumPageId;
	}

	public CategoryScoreModel setMediumPageId(int mediumPageId) {
		this.mediumPageId = mediumPageId;
		return this;
	}

	public int getHardPageId() {
		return hardPageId;
	}

	public CategoryScoreModel setHardPageId(int hardPageId) {
		this.hardPageId = hardPageId;
		return this;
	}

	public int getVeryHardPageId() {
		return veryHardPageId;
	}

	public CategoryScoreModel setVeryHardPageId(int veryHardPageId) {
		this.veryHardPageId = veryHardPageId;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public CategoryScoreModel setStatus() {
		int answeredQuestion = getTotalAnswered();
		status = (int) Calculator.getPercentage(answeredQuestion, questionCount);
		return this;
	}

	private int getTotalAnswered() {
		return veryEasyPageId + easyPageId + mediumPageId + hardPageId + veryHardPageId;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

}
