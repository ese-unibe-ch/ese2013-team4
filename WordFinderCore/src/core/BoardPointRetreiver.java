package core;

import java.util.ArrayList;

import core.board.Board;

public final class BoardPointRetreiver {

	// SINGLETON MANAGEMENT
	
	private static BoardPointRetreiver instance;
	
	public static BoardPointRetreiver getInstance() {
		if (instance == null)
			instance = new BoardPointRetreiver();
		return instance;
	}
	
	// CLASS CONTENT
	
	//empty private constructor - for singleton encapsulation
	private BoardPointRetreiver() {
	}
	
	
	public ArrayList<Point> getGoodPoints(char c, Board b, ArrayList<Point> oldPoints) {
		return this.getGoodPoints(c, b.getCharMatrix(), oldPoints);
	}
	
	public ArrayList<Point> getGoodPoints(char c, char[][] matrix, ArrayList<Point> oldPoints) {
		ArrayList<Point> goodPoints = new ArrayList<Point>();
		
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix.length; y++) {
				if (matrix[x][y] != c)
					continue;
				Point p = new Point(x, y);
				if (oldPoints.size() == 0 || (p.isAdjacent(oldPoints.get(oldPoints.size() - 1)) && ! oldPoints.contains(p)))
					goodPoints.add(p);
			}
		}
		return goodPoints;
	}
	
}
