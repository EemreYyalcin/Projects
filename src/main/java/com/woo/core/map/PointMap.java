package com.woo.core.map;

import java.util.ArrayList;

import com.woo.core.map.Side.Direction;

public class PointMap {

	public static ArrayList<Integer> getNeighbor(Side side, int mapCount) {

		if (side == null) {
			return null;
		}
		if (mapCount <= 0) {
			return null;
		}

		ArrayList<Integer> list = new ArrayList<>();
		Point point = Side.getSide(mapCount).getCurrentPoint();
		side.setCurrentPoint(point);
		Point earth = side.getPointDirection(side, Direction.EARTH);
		Point west = side.getPointDirection(side, Direction.WEST);
		Point north = side.getPointDirection(side, Direction.NORTH);
		Point south = side.getPointDirection(side, Direction.SOUTH);
		if (earth != null) {
			list.add(earth.getMapCount());
		}
		if (west != null) {
			list.add(west.getMapCount());
		}
		if (north != null) {
			list.add(north.getMapCount());
		}
		if (south != null) {
			list.add(south.getMapCount());
		}
		return list;
	}

	public static void main(String[] args) {
		ArrayList<Integer> neighbors = PointMap.getNeighbor(Side.getSide(600), 430);
		System.out.println(neighbors);
	}

}
