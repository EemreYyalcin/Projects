package com.woo.model;

public class LevelModel {

	private String name;

	private String nextUrl;

	private String imageUrl;

	private RatioModel ratioModel;
	
	private int status = 0;

	public LevelModel(String name, String nextUrl, String imageUrl, RatioModel ratioModel, int status) {
		this.name = name;
		this.nextUrl = nextUrl;
		this.imageUrl = imageUrl;
		this.setRatioModel(ratioModel);
		this.status = status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
