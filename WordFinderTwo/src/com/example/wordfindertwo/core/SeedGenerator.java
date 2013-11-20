package com.example.wordfindertwo.core;

import java.util.ArrayList;
import java.util.Random;

import com.example.wordfindertwo.core.board.Board;


public enum SeedGenerator {
	
	Instance;
	
	public final static int MAX_ATTEMPTS = 1000;
	
	public final static char SEED_SECTION_DELIMITER = '%';
	
	private Random rand;
	
	private SeedGenerator () {
		this.rand = new Random();
	}
	
	public String generateRandomSeed(IDictionary primary, IDictionary secondary, int boardSize) {
		ArrayList<String> words = new ArrayList<String>(); //list with all words placed in the matrix
		char[][] matrix = new char[boardSize][boardSize];
		
		workThrough(primary, matrix, words);
		workThrough(secondary, matrix, words);
		//fill empty fields randomly
		for (int y = 0; y < boardSize; y++) {
			for (int x = 0; x < boardSize; x++) {
				if (matrix[x][y] == '\0') {
					matrix[x][y] = (char) (65 + rand.nextInt(26)); //random letter between 'A' (65) and 'Z' (90)
				}
			}
		}
		//parse seed
		return this.parseIntoSeed(matrix, words);
	}
	
	private void workThrough(IDictionary dictionary, char[][] matrix, ArrayList<String> words) {
		if (dictionary == null)
			return;
		ArrayList<String> dic = dictionary.getWords();
		for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
			String word = dic.get(this.rand.nextInt(dic.size()));
			if (words.contains(word))
				continue;
			if (this.placeWord(matrix, word.toCharArray()))
				words.add(word);
		}
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
			ArrayList<Point> legals = BoardPointRetreiver.Instance.getGoodPoints(letter, matrix, usedPoints);
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
		
		return this.parseIntoSeed(board.getCharMatrix(), board.getWordsInBoard());
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

	private String parseIntoSeed(char[][] matrix, ArrayList<String> words) {
		String seed = "";
		//convert 2D char matrix into string (row by row)
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				seed += matrix[x][y];
			}
		}
		//add all words to the seed
		for (String word : words) {
			seed += SEED_SECTION_DELIMITER + word;
		}
		return seed;
	}
}
