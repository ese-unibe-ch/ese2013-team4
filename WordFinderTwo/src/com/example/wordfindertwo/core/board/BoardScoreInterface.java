package com.example.wordfindertwo.core.board;

import com.example.wordfindertwo.core.GameResult;

public interface BoardScoreInterface {

	/**
	 * @return sum of the scores for the found words
	 */
	public int getBoardScore();

	public int getTotalWordCount();

	public int getFoundWordCount();

	/**
	 * @return true when all words of the board have been found, false
	 *         otherwise.<br/>
	 *         i.e. <tt>TotalWordCount == FoundWordCount</tt>
	 */
	public boolean isCompleted();

	public GameResult getGameResult();

	/**
	 * defines the board's score to be valid. Only scores of boards that have
	 * been completed or played until time ran out are considered valid and may
	 * be stored in the database.
	 */
	public void setGameValid();
}
