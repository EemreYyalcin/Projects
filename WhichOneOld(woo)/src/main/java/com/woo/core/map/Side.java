package com.woo.core.map;

import java.util.Properties;

public class Side {

	private Point previousPoint;
	private Point currentPoint;
	private Properties map = new Properties();

	public Side() {
		previousPoint = new Point(1000, 1000, 1);
		currentPoint = new Point(1000, 1000, 1);
		getMap().put(currentPoint.toString(), currentPoint);
	}

	public static Side getSide(int mapCount) {
		Side side = new Side();
		while (side.getCurrentPoint().getMapCount() < mapCount){
			side = side.step(side);
		}
		return side;
	}

	public Side step(Side side) {
		if (side == null) {
			return null;
		}

		if (getDirection(side) == null) {
			return side;
		}

		if (getDirection(side) == Direction.STATIC) {
			// Keep Going Earth
			side.setPreviousPoint(side.getCurrentPoint());
			side.setCurrentPoint(new Point(side.getCurrentPoint().getX() + 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount() + 1));
			getMap().put(side.getCurrentPoint().toString(), side.getCurrentPoint());
			return side;
		}

		if (getDirection(side) == Direction.EARTH) {
			side.setPreviousPoint(side.getCurrentPoint());
			if (getMap().get((new Point(side.getCurrentPoint().getX(), side.getCurrentPoint().getY() + 1, side.getCurrentPoint().getMapCount())).toString()) == null) {
				// Turn Left
				side.setCurrentPoint(new Point(side.getCurrentPoint().getX(), side.getCurrentPoint().getY() + 1, side.getCurrentPoint().getMapCount() + 1));
			} else {
				// Keep Going
				side.setCurrentPoint(new Point(side.getCurrentPoint().getX() + 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount() + 1));

			}
		} else if (getDirection(side) == Direction.WEST) {
			side.setPreviousPoint(side.getCurrentPoint());
			if (getMap().get((new Point(side.getCurrentPoint().getX(), side.getCurrentPoint().getY() - 1, side.getCurrentPoint().getMapCount())).toString()) == null) {
				// Turn Left
				side.setCurrentPoint(new Point(side.getCurrentPoint().getX(), side.getCurrentPoint().getY() - 1, side.getCurrentPoint().getMapCount() + 1));
			} else {
				// Go head
				side.setCurrentPoint(new Point(side.getCurrentPoint().getX() - 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount() + 1));

			}
		} else if (getDirection(side) == Direction.NORTH) {
			side.setPreviousPoint(side.getCurrentPoint());
			if (getMap().get((new Point(side.getCurrentPoint().getX() - 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount())).toString()) == null) {
				// Turn Left
				side.setCurrentPoint(new Point(side.getCurrentPoint().getX() - 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount() + 1));
			} else {
				// Go Head
				side.setCurrentPoint(new Point(side.getCurrentPoint().getX(), side.getCurrentPoint().getY() + 1, side.getCurrentPoint().getMapCount() + 1));
			}

		} else if (getDirection(side) == Direction.SOUTH) {
			side.setPreviousPoint(side.getCurrentPoint());
			if (getMap().get((new Point(side.getCurrentPoint().getX() + 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount())).toString()) == null) {
				// Turn Left
				side.setCurrentPoint(new Point(side.getCurrentPoint().getX() + 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount() + 1));
			} else {
				// Go Head
				side.setCurrentPoint(new Point(side.getCurrentPoint().getX(), side.getCurrentPoint().getY() - 1, side.getCurrentPoint().getMapCount() + 1));
			}

		}
		getMap().put(side.getCurrentPoint().toString(), side.getCurrentPoint());
		return side;
	}

	public Point getPointDirection(Side side, Direction direction) {
		if (direction == Direction.EARTH) {
			Point point = (Point) side.getMap().get((new Point(side.getCurrentPoint().getX() + 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount()).toString()));
			if (point != null) {
				if (point.getMapCount() > side.getCurrentPoint().getMapCount()) {
					return null;
				}
				return point;
			} else {
				return null;
			}
		} else if (direction == Direction.WEST) {
			Point point = (Point) side.getMap().get((new Point(side.getCurrentPoint().getX() - 1, side.getCurrentPoint().getY(), side.getCurrentPoint().getMapCount()).toString()));
			if (point != null) {
				if (point.getMapCount() > side.getCurrentPoint().getMapCount()) {
					return null;
				}
				return point;
			} else {
				return null;
			}

		} else if (direction == Direction.NORTH) {
			Point point = (Point) side.getMap().get((new Point(side.getCurrentPoint().getX(), side.getCurrentPoint().getY() + 1, side.getCurrentPoint().getMapCount()).toString()));
			if (point != null) {
				if (point.getMapCount() > side.getCurrentPoint().getMapCount()) {
					return null;
				}
				return point;
			} else {
				return null;
			}

		} else if (direction == Direction.SOUTH) {
			Point point = (Point) side.getMap().get((new Point(side.getCurrentPoint().getX(), side.getCurrentPoint().getY() - 1, side.getCurrentPoint().getMapCount()).toString()));
			if (point != null) {
				if (point.getMapCount() > side.getCurrentPoint().getMapCount()) {
					return null;
				}
				return point;
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	private Direction getDirection(Side side) {
		if (side.getCurrentPoint().getX() == side.getPreviousPoint().getX()) {
			int yDifference = side.getCurrentPoint().getY() - side.getPreviousPoint().getY();
			if (yDifference > 0) {
				return Direction.NORTH;
			} else {
				return Direction.SOUTH;
			}
		} else if (side.getCurrentPoint().getY() == side.getPreviousPoint().getY()) {
			int xDifference = side.getCurrentPoint().getX() - side.getPreviousPoint().getX();
			if (xDifference > 0) {
				return Direction.EARTH;
			} else {
				return Direction.WEST;
			}
		} else {
			return Direction.STATIC;
		}
	}

	public Point getCurrentPoint() {
		return currentPoint;
	}

	public Point getPreviousPoint() {
		return previousPoint;
	}

	public void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}

	public void setPreviousPoint(Point previousPoint) {
		this.previousPoint = previousPoint;
	}

	public Properties getMap() {
		return map;
	}

	public void setMap(Properties map) {
		this.map = map;
	}

	public enum Direction {
		EARTH, NORTH, SOUTH, WEST, STATIC
	}

}
