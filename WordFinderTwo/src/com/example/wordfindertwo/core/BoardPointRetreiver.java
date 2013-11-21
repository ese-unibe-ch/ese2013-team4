package com.example.wordfindertwo.core;

import java.util.ArrayList;

public enum BoardPointRetreiver {

	Instance;

	/**
	 * returns all valid points for a certain character as the next character of
	 * a word. This character has to be at an an adjacent field to the last
	 * character of the already placed ones and has to be located on an empty
	 * field or one with the same letter and that is not yet used in the word.
	 * 
	 * @param c
	 *            the character of the next letter. This is the letter for which
	 *            all valid positions are requested.
	 * @param matrix
	 *            the current state of the board matrix. Empty fields must be
	 *            filled with the null character '\0'.
	 * @param oldPoints
	 *            a collection of all points of the word up to the letter before
	 *            the given one. The last element of this list contains the
	 *            letter that is coming directly before the given one. An empty
	 *            list is indicating, that the given letter is the first one of
	 *            the word. A <tt>null</tt> object is considered to be an empty
	 *            list.
	 */
	public ArrayList<Point> getGoodPoints(char c, char[][] matrix,
			ArrayList<Point> oldPoints) {
		ArrayList<Point> goodPoints = new ArrayList<Point>();

		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix.length; y++) {
				if (matrix[x][y] != c && matrix[x][y] != '\0')
					continue;
				Point p = new Point(x, y);
				if (oldPoints == null
						|| oldPoints.size() == 0
						|| (p.isAdjacent(oldPoints.get(oldPoints.size() - 1)) && !oldPoints
								.contains(p)))
					goodPoints.add(p);
			}
		}
		return goodPoints;
	}

}
