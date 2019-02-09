package com.woo.model;

import com.woo.core.attributes.Codes;
import com.woo.core.attributes.Link;
import com.woo.ejb.UserProperties;

public class ProfileModel {

	private String name;

	private String surname;

	private CategoryScoreModel categoryScoreModel;

	private String home;

	private String explore;

	private String myCategories;

	private String editProfile;
	
	private boolean login = false;
	
	private String loginPage;
	
	private String logoutPage;

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
	

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getLogoutPage() {
		return logoutPage;
	}

	public void setLogoutPage(String logoutPage) {
		this.logoutPage = logoutPage;
	}

	public static ProfileModel getBasicProfileModel(UserProperties userProperties) {
		ProfileModel profileModel = new ProfileModel();
		profileModel.setHome(Link.home);
		profileModel.setExplore(Link.categoryNames);
		profileModel.setMyCategories(Link.myCategory);
		profileModel.setEditProfile(Link.editProfile);
		profileModel.setLoginPage(Link.login);
		profileModel.setLogoutPage(Link.logout);
		if (userProperties.getId() == Codes.errorIntCode) {
			// Empty Model Or Demo Model
			profileModel.setName("Guess");
			profileModel.setSurname("Demo");
		}
		else {
			profileModel.setName(userProperties.getName());
			profileModel.setSurname(userProperties.getSurname());
			profileModel.setLogin(true);
		}
		return profileModel;
	}

	public String getEditProfile() {
		return editProfile;
	}

	public void setEditProfile(String editProfile) {
		this.editProfile = editProfile;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

}
