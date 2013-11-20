package com.example.wordfindertwo.core;

import java.util.ArrayList;

import com.example.wordfindertwo.core.board.Board;

public enum BoardPointRetreiver {

	Instance;
	
	public ArrayList<Point> getGoodPoints(char c, Board b, ArrayList<Point> oldPoints) {
		return this.getGoodPoints(c, b.getCharMatrix(), oldPoints);
	}
	
	public ArrayList<Point> getGoodPoints(char c, char[][] matrix, ArrayList<Point> oldPoints) {
		ArrayList<Point> goodPoints = new ArrayList<Point>();
		
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix.length; y++) {
				if (matrix[x][y] != c && matrix[x][y] != '\0')
					continue;
				Point p = new Point(x, y);
				if (oldPoints == null || oldPoints.size() == 0 || (p.isAdjacent(oldPoints.get(oldPoints.size() - 1)) && ! oldPoints.contains(p)))
					goodPoints.add(p);
			}
		}
		return goodPoints;
	}
	
}
