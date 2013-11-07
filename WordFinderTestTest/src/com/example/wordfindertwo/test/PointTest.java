package com.example.wordfindertwo.test;

import android.test.AndroidTestCase;

import com.example.wordfindertwo.core.Point;

public class PointTest extends AndroidTestCase {

	public void testSettersAndGetters() {
		Point p = new Point(1, 2);
		assertEquals(1, p.getX());
		assertEquals(2, p.getY());
		p.setX(3);
		p.setY(4);
		assertEquals(3, p.getX());
		assertEquals(4, p.getY());
	}
	
	public void testEqualityOfPoints() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(3, 4);
		assertFalse(p1.equals(p2));
		p2.setX(p1.getX());
		p2.setY(p1.getY());
		assertTrue(p1.equals(p2));
	}
	
	public void testInequalityOfDifferentTypes() {
		Point p = new Point(1, 2);
		String s = "Test";
		assertFalse(p.equals(s));
	}
	
	public void testInequalityToNull() {
		Point p = new Point(1, 2);
		assertFalse(p.equals(null));
	}
	
	public void testAdjacency() {
		Point p = new Point(1, 2);
		assertTrue(p.isAdjacent(new Point(1, 1)));
		assertTrue(p.isAdjacent(new Point(2, 2)));
		assertTrue(p.isAdjacent(new Point(2, 3)));
		assertFalse(p.isAdjacent(new Point(1, 2)));
		assertFalse(p.isAdjacent(new Point(10, 4)));
		assertTrue(p.isAdjacent(null));
	}
	
	public void testHash() {
		Point p = new Point (1, 2);
		assertEquals(3, p.hashCode());
		p = new Point(100, 23);
		assertEquals(123, p.hashCode());
	}
	
}
