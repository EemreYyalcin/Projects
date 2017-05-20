package com.woo.core.map;

public class Point {

	private int x;
	private int y;
	private int mapCount;

	public Point(int x, int y, int mapCount) {
		this.x = x;
		this.y = y;
		this.setMapCount(mapCount);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "x=" + x + ", y=" + y + "]";
	}

	public int getMapCount() {
		return mapCount;
	}

	public void setMapCount(int mapCount) {
		this.mapCount = mapCount;
	}

}
