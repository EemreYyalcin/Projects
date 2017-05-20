package com.woo.model;

public class LevelModel {

	private String name;
	private int level;
	private String nextUrl;
	private String imageUrl;

	public LevelModel(String name, int level, String nextUrl, String imageUrl) {
		this.name = name;
		this.level = level;
		this.nextUrl = nextUrl;
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

}
