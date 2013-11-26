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

	public int getScore() {
		return this.score;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
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
