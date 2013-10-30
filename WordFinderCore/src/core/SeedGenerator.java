package core;

import java.util.ArrayList;
import java.util.Random;

import core.board.Board;


public class SeedGenerator {

	// SINGLETON MANAGEMENT
	
	private static SeedGenerator singleton;
	
	public final static SeedGenerator getInstance() {
		if (singleton == null)
			singleton = new SeedGenerator();
		return singleton;
	}
	
	// CLASS CONTENT
	
	public final static int MAX_ATTEMPTS = 1000;
	
	private Random rand;
	
	private BoardPointRetreiver bpr = BoardPointRetreiver.getInstance();
	
	private SeedGenerator () {
		this.rand = new Random();
	}
	
	public String generateRandomSeed(IDictionary primary,
			IDictionary secondary, int boardSize) {
		
		assert secondary != null; //at least a secondary dictionary is required
		
		ArrayList<String> dic = (primary != null ? primary : secondary).getWords(); //select primary dic if aviable, secondary otherwise
		
		ArrayList<String> words = new ArrayList<String>(); //list with all words placed in the matrix
		
		char[][] matrix = new char[boardSize][boardSize];
		
		for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
			//STEP 1: select random word from dic
			String word = dic.get(this.rand.nextInt(dic.size()));
			
			if (words.contains(word))
				continue;
			
			if (this.placeWord(matrix, word.toCharArray()))
				words.add(word);
			
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
			ArrayList<Point> legals = bpr.getGoodPoints(letter, matrix, usedPoints);
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

	public String generateSeedFromBoard(Board board) {
		assert board != null;
		
		ILetterField[][] matrix = board.getMatrix();
		
		String seed = "";
		
		for (int y = 0; y < board.getBoardSize(); y++) {
			for (int x = 0; x < board.getBoardSize(); x++) {
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

}
