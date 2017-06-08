package com.woo.model;

public class ProfileModel {

	private String name;

	private String surname;

	private CategoryScoreModel categoryScoreModel;

	private String home;

	private String explore;

	private String myCategories;

	private String profile;

	private int veryHardClickPercentage;

	private int hardClickPercentage;

	private int mediumClickPercentage;

	private int easyClickPercentage;

	private int veryEasyClickPercentage;

	public String getName() {
		return name + " ";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname + " ";
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public CategoryScoreModel getCategoryScoreModel() {
		return categoryScoreModel;
	}

	public void setCategoryScoreModel(CategoryScoreModel categoryScoreModel) {
		this.categoryScoreModel = categoryScoreModel;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getExplore() {
		return explore;
	}

	public void setExplore(String explore) {
		this.explore = explore;
	}

	public String getMyCategories() {
		return myCategories;
	}

	public void setMyCategories(String myCategories) {
		this.myCategories = myCategories;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getVeryHardClickPercentage() {
		return veryHardClickPercentage;
	}

	public void setVeryHardClickPercentage(int veryHardClickPercentage) {
		this.veryHardClickPercentage = veryHardClickPercentage;
	}

	public int getHardClickPercentage() {
		return hardClickPercentage;
	}

	public void setHardClickPercentage(int hardClickPercentage) {
		this.hardClickPercentage = hardClickPercentage;
	}

	public int getMediumClickPercentage() {
		return mediumClickPercentage;
	}

	public void setMediumClickPercentage(int mediumClickPercentage) {
		this.mediumClickPercentage = mediumClickPercentage;
	}

	public int getEasyClickPercentage() {
		return easyClickPercentage;
	}

	public void setEasyClickPercentage(int easyClickPercentage) {
		this.easyClickPercentage = easyClickPercentage;
	}

	public int getVeryEasyClickPercentage() {
		return veryEasyClickPercentage;
	}

	public void setVeryEasyClickPercentage(int veryEasyClickPercentage) {
		this.veryEasyClickPercentage = veryEasyClickPercentage;
	}

}
