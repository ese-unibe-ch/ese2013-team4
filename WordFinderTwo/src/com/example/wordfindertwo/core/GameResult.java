package com.example.wordfindertwo.core;

public class GameResult implements Comparable<GameResult> {

	public final static double LETTER_SCORE_MULTIPLIER = 1;
	public final static double WORD_SCORE_MULTIPLIER = 10;
	public final static double TIME_SCORE_MULTIPLIER = 0.005; // half sec 1 pt
	public final static int MAX_NAME_LETTER_COUNT = 26;

	private long boardID;
	private String boardData;
	private int score;
	private String boardName;

	public GameResult(long boardID, String boardData) {
		this(boardID, boardData, 0, "");
	}

	public GameResult(long boardID, String boardData, String boardName) {
		this(boardID, boardData, 0, boardName);
	}

	public GameResult(long boardID, String boardData, int score) {
		this(boardID, boardData, score, "");
	}

	public GameResult(long boardID, String boardData, int score,
			String boardName) {
		this.boardID = boardID;
		this.boardData = boardData;
		this.score = score;
		this.boardName = boardName;
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

	public String toString() {
		return this.getShortenedName() + " \n" + this.score;
	}

	public String getName() {
		return this.boardName;
	}

	public void setName(String name) {
		this.boardName = name;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	@Override
	public int compareTo(GameResult other) {
		return this.score - other.score;
	}

	private String getShortenedName() {
		if (this.boardName.length() <= MAX_NAME_LETTER_COUNT + 2)
			return this.boardName;
		else
			return this.boardName.substring(0, MAX_NAME_LETTER_COUNT) + "...";
	}

	/**
	 * generates a serial string containing all relevant instance data
	 */
	public String serialize() {
		return "" + this.boardID + "@" + this.boardData + "@" + this.score
				+ "@" + this.boardName + "@a";
	}

	/**
	 * Creates a new GameResult instance from the serial string
	 */
	public static GameResult unserialize(String serial) {
		String[] fragments = serial.split("@");
		return new GameResult(Long.parseLong(fragments[0]), fragments[1],
				Integer.parseInt(fragments[2]), fragments[3]);
	}

}
