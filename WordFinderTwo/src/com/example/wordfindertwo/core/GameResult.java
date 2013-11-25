package com.example.wordfindertwo.core;

public class GameResult {

	private final static double LETTER_SCORE_MULTIPLIER = 1;
	private final static double WORD_SCORE_MULTIPLIER = 10;
	private final static double TIME_SCORE_MULTIPLIER = 0.005; // half sec 1 pt

	private long boardID;
	private String boardSeed;
	private int score;
	private int primaryDictionaryID;
	private int secondaryDictionaryID;

	public GameResult(long boardID, String boardSeed, int primaryID,
			int secondaryID) {
		this.boardID = boardID;
		this.boardSeed = boardSeed;
		this.score = 0;
		this.primaryDictionaryID = primaryID;
		this.secondaryDictionaryID = secondaryID;
	}

	public GameResult(long boardID, String boardSeed, int score,
			int primaryID, int secondaryID) {
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

	public String getBoardSeed() {
		return this.boardSeed;
	}

	public int getScore() {
		return this.score;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	public int getPrimaryDictionaryID() {
		return primaryDictionaryID;
	}
	
	public int getSecondaryDictionaryID() {
		return secondaryDictionaryID;
	}

	/**
	 * generates a serial string containing all relevant instance data
	 */
	public String serialize() {
		return "" + this.boardID + "@" + this.boardSeed + "@" + this.score
				+ "@" + this.primaryDictionaryID + "@"
				+ this.secondaryDictionaryID;
	}

	/**
	 * Creates a new GameResult instance from the serial string
	 */
	public static GameResult unserialize(String serial) {
		String[] fragments = serial.split("@");
		return new GameResult(Integer.parseInt(fragments[0]), fragments[1],
				Integer.parseInt(fragments[2]), Integer.parseInt(fragments[3]),
				Integer.parseInt(fragments[4]));
	}
}
