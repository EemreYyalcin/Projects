package com.woo.model;

import com.woo.domain.Ratio;

public class RatioModel {

	private int trueCount;

	private int falseCount;

	private int truePercentage;

	private int falsePercentage;

	public RatioModel() {
	}

	public RatioModel(int trueCount, int falseCount) {
		this.trueCount = trueCount;
		this.falseCount = falseCount;
		setPercentage();
	}

	public RatioModel(Ratio ratio) {
		this.trueCount = ratio.getTrueCount();
		this.falseCount = ratio.getFalseCount();
		setPercentage();
	}

	public void setPercentage() {
		int totalCount = trueCount + falseCount;
		if (totalCount <= 0) {
			this.truePercentage = 0;
			this.falsePercentage = 0;
			return;
		}
		float tPercentage = ((float) trueCount / totalCount) * 100;
		this.truePercentage = (int) tPercentage;
		this.falsePercentage = 100 - this.truePercentage;
	}

	public int getTrueCount() {
		return trueCount;
	}

	public void setTrueCount(int trueCount) {
		this.trueCount = trueCount;
	}

	public int getFalseCount() {
		return falseCount;
	}

	public void setFalseCount(int falseCount) {
		this.falseCount = falseCount;
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

	public int getTotalCount() {
		return trueCount + falseCount;
	}

	public RatioModel addRatioModel(RatioModel ratioModel) {
		int totalTrueCount = ratioModel.getTrueCount() + this.getTrueCount();
		int totalFalseCount = ratioModel.getFalseCount() + this.getFalseCount();
		return new RatioModel(totalTrueCount, totalFalseCount);
	}

	public static RatioModel getRatioModel(Ratio ratio) {
		if (ratio == null) {
			return new RatioModel(0, 0);
		}
		return new RatioModel(ratio.getTrueCount(), ratio.getFalseCount());
	}
	
	
	public static int setTotalClickPercentage(RatioModel ratioModel) {
		int totalCount = ratioModel.getTotalCount();

		float totalPercentage = 0;
		if (totalCount < 100) {
			totalPercentage = totalCount;
		}
		else if (totalCount < 500) {
			totalPercentage = ((float) ((float) totalCount / (500))) * 100;
		}
		else if (totalCount < 1000) {
			totalPercentage = ((float) ((float) totalCount / (1000))) * 100;
		}
		else if (totalCount < 10000) {
			totalPercentage = ((float) ((float) totalCount / (10000))) * 100;
		}

		return (int) totalPercentage;
	}


}
