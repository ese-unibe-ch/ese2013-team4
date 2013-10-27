package core;

import java.util.ArrayList;

import core.board.Board;

/**
 * Singleton class for finding all words of the board.
 */
public class WordFinder {

	private static WordFinder instance;
	
	public static WordFinder getInstance() {
		if (instance == null)
			instance = new WordFinder();
		return instance;
	}
	
	private WordFinder() {
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
			//check if is contained in a word from the board
			for (String goodWord : wordsInBoard) {
				if (goodWord.contains(word)) {
					wordsInBoard.add(word);
					isGood = true;
					break;
				}
			}
			if (isGood)
				continue;
			for (String badWord : wordsNotInBoard) {
				if(word.contains(badWord)) {
					wordsNotInBoard.add(word);
					isGood = true;
					break;
				}
			}
			if (isGood)
				continue;
		}
		return wordsInBoard;
	}
	
	private boolean recursiveWordCheck(ArrayList<Point> lasts, char[][] matrix, char c) {
		
		
		return false;
	}
	
}
