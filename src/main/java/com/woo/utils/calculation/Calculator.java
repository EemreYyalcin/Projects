package com.woo.utils.calculation;

public class Calculator {

	public static float getPercentage(int fraction, int total) {
		if (total == 0) {
			return 0;
		}
		float percentage = ((fraction * 100) / total);
		return percentage;
	}

}
