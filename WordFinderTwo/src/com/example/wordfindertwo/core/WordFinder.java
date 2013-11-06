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
	
	private WordFinder() {
		this.bpr = BoardPointRetreiver.getInstance();
	}
	
	public ArrayList<String> getWords(Board board) {
		char[][] matrix = board.getCharMatrix();
		//retrieving words
		ArrayList<String> completeList = new ArrayList<String>();
		if (board.hasPrimaryDictionary()) {
			Log.i("WRDF", "primary present");
			completeList.addAll(this.getWordsOfDictionary(matrix, board.getPrimaryDictionary()));
		} else {
			Log.i("WRDF", "secondary only");
		}
		Log.d("WRDF", "primary words: " + completeList.size());
		completeList.addAll(this.getWordsOfDictionary(matrix, board.getSecondaryDictionary()));
		Log.d("WRDF", "total words: " + completeList.size());
		return completeList;
	}
	
	private ArrayList<String> getWordsOfDictionary(char[][] matrix, IDictionary dic) {
		ArrayList<String> wordsInBoard = new ArrayList<String>();
		ArrayList<String> wordsNotInBoard = new ArrayList<String>();
		ArrayList<String> dictionary = dic.getWords();
		
		for (String word : dictionary) {
			Log.i("WRDF.finding", "checking word " + word);
			boolean isGood = false;
			//white-listing of larger word (word with the current word as substring)
			for (String goodWord : wordsInBoard) {
				if (goodWord.contains(word)) {
					wordsInBoard.add(word);
					Log.d("WRDF.finding", word + " is good");
					isGood = true;
					break;
				}
			}
			if (isGood) {
				continue;
			}
			//black-listing of a smaller word (word that is a substring of the current word)
			for (String badWord : wordsNotInBoard) {
				if(word.contains(badWord)) {
					wordsNotInBoard.add(word);
					Log.d("WRDF.finding", word + " is good");
					isGood = true;
					break;
				}
			}
			if (isGood) {
				continue;
			}
			if (recursiveWordCheck(new ArrayList<Point>(), matrix, word)) {
				wordsInBoard.add(word);
				Log.d("WRDF.finding", word + " is good");
			} else {
				wordsNotInBoard.add(word);
				Log.d("WRDF.finding", word + " is not");
			}
		}
		
		return wordsInBoard;
		
	}
	
	private boolean recursiveWordCheck(ArrayList<Point> lasts, char[][] matrix, String word) {
		ArrayList<Point> legals = this.bpr.getGoodPoints(word.charAt(0), matrix, lasts);
		if (word.length() == 1 && legals.size() > 0)
			return true;

		Log.d("WRDF.find.recursion", "checking sequence:   " + word);
		Log.d("WRDF.find.recursion", "possibilities found: " + legals.size());
		
		String newWord = word.substring(1);
		
		
		for (int i = 0; i < legals.size(); i++) {
			ArrayList<Point> pointList = new ArrayList<Point>(lasts);
			pointList.add(legals.get(i));
			if (this.recursiveWordCheck(pointList, matrix, newWord))
				return true;
		}
		return false;
	}
	
}
