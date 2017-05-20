package com.woo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.woo.domain.Category;
import com.woo.utils.LogMessage;

public class CategoryModel {

	private String categoryName;
	private long categoryId;
	private int decade;
	private String lastUpdateDate;
	private String categoryClickUrl;
	private String imageResource;
	private ArrayList<Integer> decadeList;

	public static CategoryModel getCategoryModel(Category category) {
		CategoryModel model = new CategoryModel();
		model.setCategoryId(category.getId()).setCategoryName(category.getName()).setDecade(category.getDecade()).setLastUpdateDate(category.getLastUpdateDate());
		return model;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public CategoryModel setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public CategoryModel setCategoryId(long categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public int getDecade() {
		return decade;
	}

	public CategoryModel setDecade(int decade) {
		this.decade = decade;
		return this;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public CategoryModel setLastUpdateDate(Date lastUpdateDate) {
		try {
			if (lastUpdateDate == null) {
				LogMessage.error("Setting CategoryModel LastUpdateDate Error. Code:Cola");
				return this;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
			this.lastUpdateDate = sdf.format(lastUpdateDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public String getCategoryClickUrl() {
		return categoryClickUrl;
	}

	public void setCategoryClickUrl(String categoryClickUrl) {
		this.categoryClickUrl = categoryClickUrl;
	}

	public String getImageResource() {
		return imageResource;
	}

	public void setImageResource(String imageResource) {
		this.imageResource = imageResource;
	}

	public String getDecadeList() {
		String temp = "";
		for (int i = 0; i < decadeList.size(); i++) {
			temp += decadeList.get(i);
			if (i != (decadeList.size() - 1)) {
				temp += " ,";
			}
		}
		return temp;
	}

	public void setDecadeList(ArrayList<Integer> decadeList) {
		this.decadeList = decadeList;
	}

}
