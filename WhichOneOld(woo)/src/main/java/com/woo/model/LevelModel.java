package com.woo.model;

public class LevelModel {

	private String name;

	private String nextUrl;

	private String imageUrl;

	private RatioModel ratioModel;

	public LevelModel(String name, String nextUrl, String imageUrl, RatioModel ratioModel) {
		this.name = name;
		this.nextUrl = nextUrl;
		this.imageUrl = imageUrl;
		this.setRatioModel(ratioModel);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public RatioModel getRatioModel() {
		return ratioModel;
	}

	public void setRatioModel(RatioModel ratioModel) {
		this.ratioModel = ratioModel;
	}

}
