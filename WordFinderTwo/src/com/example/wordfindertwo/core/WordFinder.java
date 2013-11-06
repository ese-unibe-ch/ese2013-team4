package com.example.wordfindertwo.core;

import java.util.ArrayList;

import android.util.Log;

import com.example.wordfindertwo.core.board.Board;

/**
 * Singleton class for finding all words of the board.
 */
public class WordFinder {
	
	// SINGLETON MANAGEMENT

	private static WordFinder instance;
	
	public static WordFinder getInstance() {
		if (instance == null)
			instance = new WordFinder();
		return instance;
	}
	
	// CLASS CONTENT
	
	private BoardPointRetreiver bpr;
	
	private int wordCounter;
	
	private ArrayList<String> wordsInBoard;
	
	private WordFinder() {
		this.bpr = BoardPointRetreiver.getInstance();
		this.wordCounter = 0;
		this.wordsInBoard = new ArrayList<String>();
	}
	
	public ArrayList<String> getWords(Board board) {
		char[][] matrix = board.getCharMatrix();
		
		this.wordsInBoard = new ArrayList<String>();
		this.wordCounter = 0;
		
		if (board.hasPrimaryDictionary())
			this.getWordsOfDictionary(matrix, board.getPrimaryDictionary());
		this.getWordsOfDictionary(matrix, board.getSecondaryDictionary());
		
		Log.d("WordFinder", "Word List: " + this.wordsInBoard.toString());
		Log.d("WordFinder", "Word Count: " + this.wordCounter);
		
		return this.wordsInBoard;
	}
	
	/**
	 * @return The Word Count from the last check
	 */
	public int getWordCount() {
		return this.wordCounter;
	}
	
	private ArrayList<String> getWordsOfDictionary(char[][] matrix, IDictionary dic) {
		
		ArrayList<String> dictionary = dic.getWords();
		
		for (String word : dictionary) {
			int amount = recursiveWordCheck(new ArrayList<Point>(), matrix, word);
			if (amount > 0 && ! this.wordsInBoard.contains(word)) {
				wordsInBoard.add(word);
				this.wordCounter += amount;
			}
		}
		return wordsInBoard;
		
	}
	
	private int recursiveWordCheck(ArrayList<Point> lasts, char[][] matrix, String word) {
		ArrayList<Point> legals = this.bpr.getGoodPoints(word.charAt(0), matrix, lasts);
		if (word.length() == 1 && legals.size() > 0)
			return 1;
		
		String newWord = word.substring(1);
		
		int count = 0;
		
		for (int i = 0; i < legals.size(); i++) {
			ArrayList<Point> pointList = new ArrayList<Point>(lasts);
			pointList.add(legals.get(i));
			count += this.recursiveWordCheck(pointList, matrix, newWord);
		}
		return count;
	}
	
}
