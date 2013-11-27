package com.example.wordfindertwo.core;

public class Point {
	
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	@Override
	public String toString() {
		return "POINT (" + this.x + "|" + this.y + ")";
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Point)) {
			return false;
		}
		Point pt = (Point) other;
		
		return this.x == pt.x && this.y == pt.y;
	}
	
	@Override
	public int hashCode() {
		return 10 * this.x + this.y;
	}
	
	public boolean isAdjacent (Point other) {
		return other == null || (Math.abs(this.x - other.x) <= 1 && Math.abs(this.y - other.y) <= 1 && ! this.equals(other));
	}

}
