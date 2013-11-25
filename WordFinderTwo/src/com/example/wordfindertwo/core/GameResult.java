package com.example.wordfindertwo.core;

public class GameResult {

	private final static double LETTER_SCORE_MULTIPLIER = 1;
	private final static double WORD_SCORE_MULTIPLIER = 10;
	private final static double TIME_SCORE_MULTIPLIER = 0.005; // half sec 1 pt

	private long boardID;
	private String boardData;
	// TODO: remove fields when removing deprecated stuff
	private String boardSeed;
	private int score;
	private int primaryDictionaryID;
	private int secondaryDictionaryID;

	public GameResult(long boardID, String boardData) {
		this(boardID, boardData, 0);
	}

	public GameResult(long boardID, String boardData, int score) {
		this.boardID = boardID;
		this.boardData = boardData;
		this.score = score;
	}

	public String getBoardData() {
		return this.boardData;
	}

	/** @deprecated use new GameResult(long, String) instead. */
	public GameResult(long boardID, String boardSeed, int primaryID,
			int secondaryID) {
		this.boardID = boardID;
		this.boardSeed = boardSeed;
		this.score = 0;
		this.primaryDictionaryID = primaryID;
		this.secondaryDictionaryID = secondaryID;
	}

	/** @deprecated use new GameResult(long, String, int) instead. */
	public GameResult(long boardID, String boardSeed, int score, int primaryID,
			int secondaryID) {
		this(boardID, boardSeed, primaryID, secondaryID);
		this.score = score;
	}

	public void addWord(int value) {
		this.score += (int) (WORD_SCORE_MULTIPLIER + value
				* LETTER_SCORE_MULTIPLIER);
	}

	public void addTimeBonus(long timeLeft) {
		this.score += (int) timeLeft * TIME_SCORE_MULTIPLIER;
	}

	public long getBoardID() {
		return this.boardID;
	}

	/** @deprecated content obsolete with new boardData structure */
	public String getBoardSeed() {
		return this.boardSeed;
	}

	public int getScore() {
		return this.score;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	/** @deprecated content obsolete with new boardData structure */
	public int getPrimaryDictionaryID() {
		return primaryDictionaryID;
	}

	/** @deprecated content obsolete with new boardData structure */
	public int getSecondaryDictionaryID() {
		return secondaryDictionaryID;
	}

	/**
	 * generates a serial string containing all relevant instance data
	 */
	public String serialize() {
		return "" + this.boardID + "@" + this.boardData + "@" + this.score;
	}

	/**
	 * Creates a new GameResult instance from the serial string
	 */
	public static GameResult unserialize(String serial) {
		String[] fragments = serial.split("@");
		return new GameResult(Long.parseLong(fragments[0]), fragments[1],
				Integer.parseInt(fragments[2]));
	}
}
