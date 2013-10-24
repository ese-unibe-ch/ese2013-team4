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
		//retreiving words
		ArrayList<String> completeList = new ArrayList<String>();
		if (board.hasPrimaryDictionary())
			completeList.addAll(this.getWordsOfDictionary(matrix, board.getPrimaryDictionary()));
		completeList.addAll(this.getWordsOfDictionary(matrix, board.getSecondaryDictionary()));
		////////////
		return completeList;
	}
	
	private ArrayList<String> getWordsOfDictionary(char[][] matrix, IDictionary dic) {
		//TODO: implement word finding
		
		////////////
		return null;
	}
	
}
