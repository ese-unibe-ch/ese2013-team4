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
		for(int i = 0; i < dictionary.size(); i++) {
			String word = dictionary.get(i);
			//omit checking duplicates
			if (wordsInBoard.contains(word))
				continue;
			//omit words that are extensions of words that are already confirmed not to be in the board
			for (String w : wordsNotInBoard) {
				if (word.contains(w)) {
					wordsNotInBoard.add(word);
					continue;
				}
			}
			//check rest via board
			if (checkWord(matrix, word))
				wordsInBoard.add(word);
			else
				wordsNotInBoard.add(word);
		}
		return wordsInBoard;
	}
	
	private boolean checkWord(char[][] matrix, String word) {
		//TODO: Auto-generated method stub
		return false;
	}
	
}
