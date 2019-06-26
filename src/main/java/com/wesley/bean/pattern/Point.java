package com.wesley.bean.pattern;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Point {
	private final int x;
	private final int y;
	Point(int x, int y) {
	this.x = x;
	this.y = y;
	}
	public int getX() { return x; }
	
	public int getY() { return y; }
	public Point moveRightBy(int x){
	return new Point(this.x + x, this.y);
	}
	
	
	
	/*
	 * @Test public void testMoveRightBy() throws Exception { Point p1 = new
	 * Point(5, 5); Point p2 = p1.moveRightBy(10); assertEquals(15, p2.getX());
	 * assertEquals(5, p2.getY()); }
	 */
}
