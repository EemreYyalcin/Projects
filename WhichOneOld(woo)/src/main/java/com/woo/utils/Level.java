package com.woo.utils;

public class Level {

	public static int getLevel(int difference) {
		int n = fabs(difference);
		if (n <= 1) {
			return 5;
		} else if (n == 2) {
			return 4;
		} else if (n <= 4) {
			return 3;
		} else if (n <= 6) {
			return 2;
		} else {
			return 1;
		}
	}

	private static int fabs(int a) {
		if (a < 0) {
			return a * (-1);
		}
		return a;
	}

}
