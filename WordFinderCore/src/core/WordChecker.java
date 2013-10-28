package core;

import core.board.Board;

public class WordChecker {

	private static WordChecker instance;
	
	public static WordChecker getInstance() {
		if (instance == null)
			instance = new WordChecker();
		return instance;
	}
	
	private WordChecker() {
	}
	
	public boolean isValidWord(IWord word, Board board) {
		
		String wordString = word.toString();
		
		return board.getWordsInBoard().contains(wordString);
	}

}
