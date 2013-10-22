package core.gen;

import java.util.ArrayList;
import java.util.Random;

import core.IBoard;
import core.IDictionary;
import core.ILetterField;

public class BasicSeedGenerator implements ISeedGenerator {

	public final static int MAX_ATTEMPTS = 1000;
	
	private Random rand;
	
	public BasicSeedGenerator () {
		this.rand = new Random();
	}
	
	@Override
	public String generateRandomSeed(IDictionary primary,
			IDictionary secondary, int boardSize) {
		
		assert secondary != null; //at least a secondary dictionary is required
		
		ArrayList<String> dic = (primary != null ? primary : secondary).getWords(); //select primary dic if aviable, secondary otherwise
		
		ArrayList<String> words = new ArrayList<String>(); //list with all words placed in the matrix
		
		char[][] matrix = new char[boardSize][boardSize];
		
		for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
			//STEP 1: select random word from dic
			String word = dic.get(this.rand.nextInt(dic.size()));
			//STEP 2: iterate through the word
			
			if (this.placeWord(matrix, word.toCharArray())) {
				words.add(word);
			}
			
		}
		
		//LAST STEP: fill empty fields with random chars
		for (int y = 0; y < boardSize; y++) {
			for (int x = 0; x < boardSize; x++) {
				if (matrix[x][y] == '\0') {
					matrix[x][y] = (char) (65 + rand.nextInt(26)); //random letter between 'A' (65) and 'Z' (90)
				}
			}
		}
		
		//convert char matrix to seed string
		
		String seed = "";
		
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				seed += matrix[x][y];
			}
		}
		
		return seed;
	}
	
	/**
	 * Tries to place a word on the matrix
	 * 
	 * @param matrix the matrix to modify with the word
	 * @param word the word to be placed
	 * @return true when word was placed, false otherwise
	 */
	private boolean placeWord(char[][] matrix, char[] word) {
		char[][] backup = this.copyMatrix(matrix);
		ArrayList<Point> usedPoints = new ArrayList<Point>();
		for (int i = 0; i < word.length; i++) {
			char letter = word[i];
			ArrayList<Point> legals = this.getLegal(matrix, letter, usedPoints);
			if (legals.isEmpty()) {
				matrix = backup;
				return false;
			}
			Point position = legals.get(rand.nextInt(legals.size()));
			usedPoints.add(position);
			matrix[position.getX()][position.getY()] = letter;
		}
		//this point is only reached when word was completely placed
		return true;
	}
	
	//returns a list with all legal positions of the next character
	private ArrayList<Point> getLegal(char[][] matrix, char letter, ArrayList<Point> usedPoints) {
		int max = matrix.length - 1;
		ArrayList<Point> legals = new ArrayList<Point>();
		//check all surrounding fields
		int x = usedPoints.get(usedPoints.size() - 1).getX();
		int y = usedPoints.get(usedPoints.size() - 1).getY();
		
		// '\0' is the null character '\0' <==> 0; initial value of chars (and therefore for all unused fields)
		if (x > 0   && y > 0   && !usedPoints.contains(new Point(x - 1, y - 1)) && (matrix[x - 1][y - 1] == letter || matrix[x - 1][y - 1] == '\0')) { legals.add(new Point(x - 1, y - 1)); }
		if (x > 0   &&            !usedPoints.contains(new Point(x - 1, y    )) && (matrix[x - 1][y    ] == letter || matrix[x - 1][y    ] == '\0')) { legals.add(new Point(x - 1, y    )); }
		if (x > 0   && y < max && !usedPoints.contains(new Point(x - 1, y + 1)) && (matrix[x - 1][y + 1] == letter || matrix[x - 1][y + 1] == '\0')) { legals.add(new Point(x - 1, y + 1)); }
		if (           y > 0   && !usedPoints.contains(new Point(x    , y - 1)) && (matrix[x    ][y - 1] == letter || matrix[x    ][y - 1] == '\0')) { legals.add(new Point(x    , y - 1)); }
		if (           y < max && !usedPoints.contains(new Point(x   ,  y + 1)) && (matrix[x    ][y + 1] == letter || matrix[x    ][y + 1] == '\0')) { legals.add(new Point(x    , y + 1)); }
		if (x < max && y > 0   && !usedPoints.contains(new Point(x + 1, y - 1)) && (matrix[x + 1][y - 1] == letter || matrix[x + 1][y - 1] == '\0')) { legals.add(new Point(x + 1, y - 1)); }
		if (x < max &&            !usedPoints.contains(new Point(x + 1, y    )) && (matrix[x + 1][y    ] == letter || matrix[x + 1][y    ] == '\0')) { legals.add(new Point(x + 1, y    )); }
		if (x < max && y < max && !usedPoints.contains(new Point(x + 1, y + 1)) && (matrix[x + 1][y + 1] == letter || matrix[x + 1][y + 1] == '\0')) { legals.add(new Point(x + 1, y + 1)); }
		
		return legals;
	}

	@Override
	public String generateSeedFromBoard(IBoard board) {
		assert board != null;
		
		ILetterField[][] matrix = board.getMatrix();
		
		String seed = "";
		
		for (int y = 0; y < board.getSize(); y++) {
			for (int x = 0; x < board.getSize(); x++) {
				seed += matrix[x][y].getLetter().getChar();
			}
		}
		
		return seed;
	}
	
	private char[][] copyMatrix (char[][] matrix) {
		char[][] copy = new char[matrix.length][matrix.length];
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				copy[x][y] = matrix[x][y];
			}
		}
		return copy;
	}
	
	private class Point {
		private int x;
		private int y;
		
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
	}

}
