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
		
		char[][] oldMatrix = this.copyMatrix(matrix);
		
		for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
			//STEP 1: select random word from dic
			char[] word = dic.get(this.rand.nextInt(dic.size())).toCharArray();
			//STEP 2: iterate through the word
			
			int[][] fields = new int[word.length][2];
			
			for (int i = 0; i < word.length; i++) {
				//STEP 2.1: get list of all good fields (empty or matching letter adjacent to last and not yet used in word / anywhere for first)
				
				//STEP 2.2.1: set letter on random field if there are any possibilities
				
				//STEP 2.2.2: revert to backup matrix if no possibilities
				
			}
		}
		
		//LAST STEP: fill empty fields with random chars
		
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
				return backup;
			}
			Point position = legals.get(rand.nextInt(legals.size()));
			usedPoints.add(position);
			matrix[position.getX()][position.getY()] = letter;
		}
		//this point is only reached when word was completely placed
		return matrix;
	}
	
	//returns a list with all legal positions of the next character
	private ArrayList<Point> getLegal(char[][] matrix, char letter, ArrayList<Point> usedPoints) {
		int max = matrix.length - 1;
		ArrayList<Point> legals = new ArrayList<Point>();
		//check all surrounding fields
		int x = usedPoints.get(usedPoints.size() - 1).getX();
		int y = usedPoints.get(usedPoints.size() - 1).getY();
		
		if (x > 0   && y > 0   && !usedPoints.contains(new Point(x - 1, y - 1)) && matrix[x - 1][y - 1] == letter) { legals.add(new Point(x - 1, y - 1)); }
		if (x > 0   &&            !usedPoints.contains(new Point(x - 1, y    )) && matrix[x - 1][y    ] == letter) { legals.add(new Point(x - 1, y    )); }
		if (x > 0   && y < max && !usedPoints.contains(new Point(x - 1, y + 1)) && matrix[x - 1][y + 1] == letter) { legals.add(new Point(x - 1, y + 1)); }
		if (           y > 0   && !usedPoints.contains(new Point(x    , y - 1)) && matrix[x    ][y - 1] == letter) { legals.add(new Point(x    , y - 1)); }
		if (           y < max && !usedPoints.contains(new Point(x   ,  y + 1)) && matrix[x    ][y + 1] == letter) { legals.add(new Point(x    , y + 1)); }
		if (x < max && y > 0   && !usedPoints.contains(new Point(x + 1, y - 1)) && matrix[x + 1][y - 1] == letter) { legals.add(new Point(x + 1, y - 1)); }
		if (x < max &&            !usedPoints.contains(new Point(x + 1, y    )) && matrix[x + 1][y    ] == letter) { legals.add(new Point(x + 1, y    )); }
		if (x < max && y < max && !usedPoints.contains(new Point(x + 1, y + 1)) && matrix[x + 1][y + 1] == letter) { legals.add(new Point(x + 1, y + 1)); }
		
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
