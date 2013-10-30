package core.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import core.BoardPointRetreiver;
import core.Point;

public class BoardPointRetreiverTest {

	@Test
	public void testEmptyMatrix() {
		BoardPointRetreiver ret = BoardPointRetreiver.getInstance();
		
		char[][] test = new char[3][3];
		
		ArrayList<Point> res = ret.getGoodPoints('A', test, null);
		
		assertEquals(9, res.size());
		
		res = ret.getGoodPoints('A', test, new ArrayList<Point>());
	}
	
	@Test
	public void testPartiallyFilledMatrix() {
		BoardPointRetreiver ret = BoardPointRetreiver.getInstance();
		
		char[][] test = new char[3][3];
		
		test[0][0] = 'A';
		test[0][1] = 'B';
		test[0][2] = 'C';
		
		ArrayList<Point> res = ret.getGoodPoints('A', test, null);
		
		assertEquals(7, res.size());
		assertFalse(res.contains(new Point(0, 1)));
		assertFalse(res.contains(new Point(0, 2)));
		
		res = ret.getGoodPoints('A', test, new ArrayList<Point>());
		
		assertEquals(7, res.size());
		assertFalse(res.contains(new Point(0, 1)));
		assertFalse(res.contains(new Point(0, 2)));
	}
	
	@Test
	public void testEmptyProceeding() {
		BoardPointRetreiver ret = BoardPointRetreiver.getInstance();
		
		char[][] test = new char[3][3];
		
		test[1][1] = 'A';
		
		ArrayList<Point> lst = new ArrayList<Point>();
		lst.add(new Point(1, 1));
		
		ArrayList<Point> res = ret.getGoodPoints('A', test, lst);
		
		assertEquals(8, res.size());
		assertFalse(res.contains(new Point(1, 1)));
	}
	
	@Test
	public void testEmptyProceedingWithOlds() {
		BoardPointRetreiver ret = BoardPointRetreiver.getInstance();
		
		char[][] test = new char[3][3];
		
		test[0][0] = 'A';
		test[1][1] = 'A';
		
		ArrayList<Point> lst = new ArrayList<Point>();
		lst.add(new Point(0, 0));
		lst.add(new Point(1, 1));
		
		ArrayList<Point> res = ret.getGoodPoints('A', test, lst);
		
		assertEquals(7, res.size());
		assertFalse(res.contains(new Point(0, 0)));
		assertFalse(res.contains(new Point(1, 1)));
	}
	
	@Test
	public void testProceedingWithExisting() {
		BoardPointRetreiver ret = BoardPointRetreiver.getInstance();
		
		char[][] test = new char[3][3];
		
		test[0][0] = 'A';
		test[1][1] = 'A';
		test[1][2] = 'B';
		test[2][2] = 'C';
		
		ArrayList<Point> lst = new ArrayList<Point>();
		lst.add(new Point(0, 0));
		lst.add(new Point(1, 1));
		
		ArrayList<Point> res = ret.getGoodPoints('B', test, lst);
		
		assertEquals(6, res.size());
		assertFalse(res.contains(new Point(0, 0)));
		assertFalse(res.contains(new Point(1, 1)));
		assertTrue(res.contains(new Point(1, 2)));
		assertFalse(res.contains(new Point(2, 2)));
	}

}
