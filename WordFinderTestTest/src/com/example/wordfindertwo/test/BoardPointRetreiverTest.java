package com.example.wordfindertwo.test;

import java.util.ArrayList;

import com.example.wordfindertwo.core.BoardPointRetreiver;
import com.example.wordfindertwo.core.Point;

import android.test.AndroidTestCase;

public class BoardPointRetreiverTest extends AndroidTestCase {

	BoardPointRetreiver bpr;
	char matrix[][];
	
	@Override
	protected void setUp() throws Exception {
		this.bpr = BoardPointRetreiver.getInstance();
		this.matrix = new char[6][6];
	}
	
	public void testBrpOk() {
		assertNotNull(this.bpr);
		assertEquals('\0', this.matrix[0][0]);
	}
	
	public void testEmptyBoard() {
		assertEquals(36, this.bpr.getGoodPoints('A', this.matrix, null));
		assertEquals(36, this.bpr.getGoodPoints('a', this.matrix, null));
		assertEquals(36, this.bpr.getGoodPoints('A', this.matrix, new ArrayList<Point>()));
		assertEquals(36, this.bpr.getGoodPoints('a', this.matrix, new ArrayList<Point>()));
	}
	
	public void testBoardWithBadExistings() {
		this.matrix[0][0] = 'B';
		assertEquals(35, this.bpr.getGoodPoints('A', this.matrix, null).size());
	}

	public void testSecondPointOnEmptyBoard() {
		this.matrix[0][0] = 'B';
		ArrayList<Point> olds = new ArrayList<Point>();
		olds.add(new Point(0, 0));
		assertEquals(3, this.bpr.getGoodPoints('A', this.matrix, olds).size());
	}
	
	public void testSecondPointOnBoardWithSameLetter() {
		this.matrix[0][0] = 'B';
		this.matrix[0][1] = 'A';
		ArrayList<Point> olds = new ArrayList<Point>();
		olds.add(new Point(0, 0));
		assertEquals(3, this.bpr.getGoodPoints('A', this.matrix, olds).size());
	}
	
	public void testSecondPointOnBoardWithDifferentLetter() {
		this.matrix[0][0] = 'B';
		this.matrix[0][1] = 'C';
		ArrayList<Point> olds = new ArrayList<Point>();
		olds.add(new Point(0, 0));
		assertEquals(2, this.bpr.getGoodPoints('A', this.matrix, olds).size());
		assertTrue(this.bpr.getGoodPoints('A', this.matrix, olds).contains(new Point(1, 0)));
		assertTrue(this.bpr.getGoodPoints('A', this.matrix, olds).contains(new Point(1, 1)));
	}
	
	public void testSecondPointOnBoardWithNoSolution() {
		this.matrix[0][0] = 'B';
		this.matrix[0][1] = 'C';
		this.matrix[1][0] = 'D';
		this.matrix[1][1] = 'E';
		ArrayList<Point> olds = new ArrayList<Point>();
		olds.add(new Point(0, 0));
		assertEquals(0, this.bpr.getGoodPoints('A', this.matrix, olds).size());
	}
	
	public void testSecondPointSameAsFirst() {
		this.matrix[0][0] = 'A';
		this.matrix[0][1] = 'C';
		this.matrix[1][0] = 'D';
		this.matrix[1][1] = 'E';
		ArrayList<Point> olds = new ArrayList<Point>();
		olds.add(new Point(0, 0));
		assertEquals(0, this.bpr.getGoodPoints('A', this.matrix, olds).size()); //size should still be zero, since the 'A' at (0|0) cannot be used as it's an old point
	}
	
}
