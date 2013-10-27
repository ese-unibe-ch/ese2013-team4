package core;

import java.util.ArrayList;

import core.board.Board;

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
		//converting board into char matrix
		char[][] matrix = new char[board.getBoardSize()][board.getBoardSize()];
		for (int x = 0; x < board.getBoardSize(); x++) {
			for (int y = 0; y < board.getBoardSize(); y++) {
				matrix[x][y] = board.getCharAt(x, y);
			}
		}
		//retrieving words
		ArrayList<String> completeList = new ArrayList<String>();
		if (board.hasPrimaryDictionary())
			completeList.addAll(this.getWordsOfDictionary(matrix, board.getPrimaryDictionary()));
		completeList.addAll(this.getWordsOfDictionary(matrix, board.getSecondaryDictionary()));
		return completeList;
	}
	
	private ArrayList<String> getWordsOfDictionary(char[][] matrix, IDictionary dic) {
		ArrayList<String> wordsInBoard = new ArrayList<String>();
		ArrayList<String> wordsNotInBoard = new ArrayList<String>();
		ArrayList<String> dictionary = dic.getWords();
		
		for (String word : dictionary) {
			boolean isGood = false;
			//white-listing of larger word (word with the current word as substring)
			for (String goodWord : wordsInBoard) {
				if (goodWord.contains(word)) {
					wordsInBoard.add(word);
					isGood = true;
					break;
				}
			}
			if (isGood)
				continue;
			//black-listing of a smaller word (word that is a substring of the current word)
			for (String badWord : wordsNotInBoard) {
				if(word.contains(badWord)) {
					wordsNotInBoard.add(word);
					isGood = true;
					break;
				}
			}
			if (isGood)
				continue;
			if (recursiveWordCheck(new ArrayList<Point>(), matrix, word)) {
				wordsInBoard.add(word);
			} else {
				wordsNotInBoard.add(word);
			}
		}
		
		return wordsInBoard;
		
	}
	
	private boolean recursiveWordCheck(ArrayList<Point> lasts, char[][] matrix, String word) {
		
		ArrayList<Point> legals = this.bpr.getGoodPoints(word.charAt(0), matrix, lasts);
		
		if (word.length() == 1 && legals.size() > 0)
			return true;
		
		String newWord = word.substring(1);
		
		for (int i = 0; i < legals.size(); i++) {
			ArrayList<Point> pointList = new ArrayList<Point>(lasts);
			pointList.add(legals.get(i));
			if (this.recursiveWordCheck(lasts, matrix, newWord))
				return true;
		}
		
		return false;
	}
	
}
