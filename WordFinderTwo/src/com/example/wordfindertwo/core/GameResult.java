package com.example.wordfindertwo.core;

public final class GameResult {

	private final static double LETTER_SCORE_MULTIPLIER = 1;
	private final static double WORD_SCORE_MULTIPLIER = 10;
	private final static double TIME_SCORE_MULTIPLIER = 0.5;

	private int boardID;
	private String boardSeed;
	private int score;
	private boolean isValid; // true only when board finished correctly

	public GameResult(int boardID, String boardSeed) {
		this.boardID = boardID;
		this.boardSeed = boardSeed;
		this.score = 0;
		this.isValid = false;
	}
	
	private GameResult(int boardID, String boardSeed, int score, boolean isValid) {
		this(boardID, boardSeed);
		this.score = score;
		this.isValid = isValid;
	}

	public void addWord(int value) {
		this.score += (int) (WORD_SCORE_MULTIPLIER + value
				* LETTER_SCORE_MULTIPLIER);
	}

	public void addTimeBonus(long timeLeft) {
		this.score += (int) timeLeft * TIME_SCORE_MULTIPLIER;
	}

	public int getBoardID() {
		return this.boardID;
	}

	public String getBoardSeed() {
		return this.boardSeed;
	}

	public int getScore() {
		return this.score;
	}

	public boolean isValid() {
		return this.isValid;
	}
	
	/**
	 * generates a serial string containing all relevant instance data
	 */
	public String serialize() {
		return "" + this.boardID + "@" + this.boardSeed + "@" + this.score + "@" + (this.isValid ? "true" : "false");
	}
	
	/**
	 * Creates a new GameResult instance from the serial string
	 */
	public static GameResult unserialize(String serial) {
		String[] fragments = serial.split("@");
		return new GameResult(Integer.parseInt(fragments[0]), fragments[1], Integer.parseInt(fragments[2]), fragments[3].equals("true"));
	}
}
