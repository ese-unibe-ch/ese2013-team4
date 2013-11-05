package com.example.wordfindertwo.core.board;

public interface BoardScoreInterface {

	/**
	 * @return sum of the scores for the found words
	 */
	public int getBoardScore();
	
	public int getTotalWordCount();
	
	public int getFoundWordCount();
	
	/**
	 * @return true when all words of the board have been found, false otherwise.<br/>
	 * 			i.e. <tt>TotalWordCount == FoundWordCount</tt>
	 */
	public boolean isCompleted();
	
}
