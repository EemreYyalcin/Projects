package com.woo.model;

import com.woo.core.attributes.Link;
import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.domain.Question;
import com.woo.domain.QuestionScore;

public class QuestionModel {

	private long questionId;
	private Item itemA;
	private Item itemB;
	private QuestionScore score;
	private int level;
	private Category category;
	private String imageResource;
	private String itemAClickNext;
	private String itemBClickNext;
	private String itemCClickNext;
	private int waitingTime = 2;
	private int totalPercentage;
	private int truePercentage;
	private int falsePercentage;
	
	
	

	public static QuestionModel getQuestionModel(Question question) {
		QuestionModel model = new QuestionModel();
		model.setCategory(question.getCategory()).setItemA(question.getItemA()).setItemB(question.getItemB()).setLevel(question.getLevel()).setQuestionId(question.getId()).setScore(question.getScore()).setImageResource(Link.imageSource);
		return model;
	}

	public long getQuestionId() {
		return questionId;
	}

	public QuestionModel setQuestionId(long questionId) {
		this.questionId = questionId;
		return this;
	}

	public Item getItemA() {
		return itemA;
	}

	public QuestionModel setItemA(Item itemA) {
		this.itemA = itemA;
		return this;
	}

	public Item getItemB() {
		return itemB;
	}

	public QuestionModel setItemB(Item itemB) {
		this.itemB = itemB;
		return this;
	}

	public QuestionScore getScore() {
		return score;
	}

	public QuestionModel setScore(QuestionScore score) {
		this.score = score;
		return this;
	}

	public int getLevel() {
		return level;
	}

	public QuestionModel setLevel(int level) {
		this.level = level;
		return this;
	}

	public Category getCategory() {
		return category;
	}

	public QuestionModel setCategory(Category category) {
		this.category = category;
		return this;
	}

	public String getImageResource() {
		return imageResource;
	}

	public void setImageResource(String imageResource) {
		this.imageResource = imageResource;
	}

	public String getItemBClickNext() {
		return itemBClickNext;
	}

	public void setItemBClickNext(String itemBClickNext) {
		this.itemBClickNext = itemBClickNext;
	}

	public String getItemAClickNext() {
		return itemAClickNext;
	}

	public void setItemAClickNext(String itemAClickNext) {
		this.itemAClickNext = itemAClickNext;
	}

	public String getItemCClickNext() {
		return itemCClickNext;
	}

	public void setItemCClickNext(String itemCClickNext) {
		this.itemCClickNext = itemCClickNext;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getTruePercentage() {
		return truePercentage;
	}

	public void setTruePercentage(int truePercentage) {
		this.truePercentage = truePercentage;
	}

	public int getFalsePercentage() {
		return falsePercentage;
	}

	public void setFalsePercentage(int falsePercentage) {
		this.falsePercentage = falsePercentage;
	}

	public int getTotalPercentage() {
		return totalPercentage;
	}

	public void setTotalPercentage(int totalPercentage) {
		this.totalPercentage = totalPercentage;
	}

}
