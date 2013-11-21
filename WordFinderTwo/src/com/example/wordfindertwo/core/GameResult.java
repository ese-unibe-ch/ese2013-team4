package com.example.wordfindertwo.core;

public final class GameResult {

	private final static double LETTER_SCORE_MULTIPLIER = 1;
	private final static double WORD_SCORE_MULTIPLIER = 10;
	private final static double TIME_SCORE_MULTIPLIER = 0.005; // half sec 1 pt

	private long boardID;
	private String boardSeed;
	private int score;

	public GameResult(long boardID, String boardSeed) {
		this.boardID = boardID;
		this.boardSeed = boardSeed;
		this.score = 0;
	}

	private GameResult(long boardID, String boardSeed, int score) {
		this(boardID, boardSeed);
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

	/**
	 * generates a serial string containing all relevant instance data
	 */
	public String serialize() {
		return "" + this.boardID + "@" + this.boardSeed + "@" + this.score;
	}

	/**
	 * Creates a new GameResult instance from the serial string
	 */
	public static GameResult unserialize(String serial) {
		String[] fragments = serial.split("@");
		return new GameResult(Integer.parseInt(fragments[0]), fragments[1],
				Integer.parseInt(fragments[2]));
	}
}
