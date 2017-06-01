package com.woo.utils;

import com.woo.domain.Ratio;
import com.woo.domain.Score;

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

	public static Ratio getLevel(Score score, int level) {
		if (score == null) {
			return null;
		}
		switch (level) {
		case 5:
			return score.getVeryHardScore();
		case 4:
			return score.getHardScore();
		case 3:
			return score.getMediumScore();
		case 2:
			return score.getEasyScore();
		case 1:
			return score.getVeryEasyScore();
		default:
			return null;
		}

	}

}
