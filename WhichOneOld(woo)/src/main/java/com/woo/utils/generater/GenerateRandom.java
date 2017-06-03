package com.woo.utils.generater;

import java.util.ArrayList;
import java.util.Random;

public class GenerateRandom {

	public static ArrayList<Integer> getRandomList(int fromIndex, int toIndex) {

		Random rand = new Random();
		ArrayList<Integer> list = new ArrayList<>();
		int size = toIndex - fromIndex;
		int count = 0;
		while (count < size) {
			int temp = rand.nextInt(size);
			int founded = temp + fromIndex;
			if (list.contains(new Integer(founded))) {
				continue;
			}
			else {
				list.add(founded);
				count++;
			}
		}
		return list;
	}

	public static int getRandomInt(int fromIndex, int toIndex) {
		Random rand = new Random();
		if (toIndex <= fromIndex) {
			return 1;
		}
		return rand.nextInt(toIndex - fromIndex) + fromIndex;
	}

	public static ArrayList<String> getRandomList(ArrayList<String> list) {
		ArrayList<Integer> randomList = GenerateRandom.getRandomList(0, list.size());
		ArrayList<String> decadeRandomItems = new ArrayList<>();
		for (int i = 0; i < randomList.size(); i++) {
			decadeRandomItems.add(list.get(randomList.get(i)));
		}
		return decadeRandomItems;
	}

}
