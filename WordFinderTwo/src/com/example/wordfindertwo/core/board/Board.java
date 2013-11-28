package com.example.wordfindertwo.core.board;

import java.util.ArrayList;

import android.util.Log;

import com.example.wordfindertwo.StandardDictionary;
import com.example.wordfindertwo.core.*;
import com.example.wordfindertwo.core.exceptions.*;

/**
 * The core's representation of a game Board. <br/>
 * 
 * A game board is considered to be the combination of a 6x6 matrix with
 * numerical (scoring) values, a collection of dictionaries, a collection of all
 * words from these dictionaries that are contained in the matrix, and a
 * collection of all found words.
 * 
 * @author ESE2013 - Team 4
 */
public class Board implements BoardDictionarySupportInterface,
		BoardDrawingInterface, BoardInputInterface, BoardOperationInterface,
		BoardScoreInterface, BoardDatabaseInterface {

	private ILetterField[][] matrix;
	private ArrayList<String> customWords;
	private int systemDictionaryID;
	private ArrayList<String> wordsInBoard;
	private ArrayList<String> foundWords;
	private long id;
	private GameResult gameResult;
	
	private StandardDictionary systemDic;
	
	public Board(long id, ILetterField[][] matrix, ArrayList<String> customWords,
			int systemDictionaryID, ArrayList<String> wordsInBoard) {
		this.matrix = matrix;
		this.customWords = customWords;
		this.systemDictionaryID = systemDictionaryID;
		this.foundWords = new ArrayList<String>();
		this.wordsInBoard = wordsInBoard;
		this.id = id;
		this.gameResult = new GameResult(this.id, this.getSeed());
		this.systemDic = StandardDictionary.getDictionary(this.systemDictionaryID);
	}

	public ILetterField[][] getMatrix() {
		return this.matrix;
	}

	/* IMPLEMENTATION OF BoardDictionarySupportInterface */

	public ArrayList<String> getCustomWords() {
		return this.customWords;
	};
	
	@Override
	public int getSystemDictionaryID() {
		return this.systemDictionaryID;
	}
	
	@Override
	public ArrayList<String> getWordsInBoard() {
		return this.wordsInBoard;
	}

	/* IMPLEMENTATION OF BoardDrawingInterface */

	@Override
	public Letter getLetterAt(int x, int y) {
		assert x >= 0 && x < this.matrix.length && y >= 0
				&& y < this.matrix.length;

		return this.matrix[x][y].getLetter();
	}

	@Override
	public char getCharAt(int x, int y) {
		return this.getLetterAt(x, y).getChar();
	}

	@Override
	public int getValueAt(int x, int y) {
		return this.getLetterAt(x, y).getValue();
	}

	@Override
	public int getBoardSize() {
		return this.matrix.length;
	}

	/* IMPLEMENTATION OF BoardInputInterface */

	/**
	 * @param sequence
	 */
	@Override
	public SelectionStatus submit(ArrayList<Point> sequence) {
		// STEP 1: check if sequence is legal (n adjacent to n-1 and no
		// identical ones).
		for (int i = 0; i < sequence.size(); i++) {
			if (sequence.lastIndexOf(sequence.get(i)) != i
					|| (i > 0 && !sequence.get(i - 1).isAdjacent(
							sequence.get(i)))) {
				Log.d("Board.submit", "Input sequence is invalid");
				return SelectionStatus.SelectionInvalid;
			}
		}
		// STEP 2: convert to string
		String word = "";
		for (Point point : sequence) {
			word += this.getLetterAt(point.getX(), point.getY()).getChar();
		}

		// STEP 3: check if sequence is in Found list.
		// if it is in list, it is an old word.
		if (!this.foundWords.isEmpty() && this.foundWords.contains(word)) {
			Log.d("Board.submit", "Word " + word + " is already found");
			return SelectionStatus.SelectionOld;
		}
		// STEP 4: check string
		// if (this.wordsInBoard.contains(word)) {
		if (this.wordsInBoard.contains(word)
				|| this.customWords.contains(word)
				|| this.systemDic.getWords().contains(word)) {
			this.foundWords.add(word);
			this.updateScore(word);
			Log.d("Board.submit", "Word " + word + " is valid");
			return SelectionStatus.SelectionGood;
		}
		// STEP 5: wrong word
		Log.d("Board.submit", "Word " + word + " is no valid word");
		return SelectionStatus.SelectionBad;
	}

	/* IMPLEMENTATION OF BoardOperationInterface */

	@Override
	public char[][] getCharMatrix() {
		char[][] matrix = new char[this.matrix.length][this.matrix.length];
		for (int x = 0; x < this.matrix.length; x++) {
			for (int y = 0; y < this.matrix.length; y++) {
				matrix[x][y] = this.matrix[x][y].getLetter().getChar();
			}
		}
		return matrix;
	}

	/* IMPLEMENTATION OF BoardScoreInterface */

	@Override
	public int getBoardScore() {
		return this.gameResult.getScore();
	}

	@Override
	public int getFoundWordCount() {
		return this.foundWords.size();
	}

	@Override
	public int getTotalWordCount() {
		return this.wordsInBoard.size();
	}

	@Override
	public boolean isCompleted() {
		return this.getTotalWordCount() == getFoundWordCount();
	}

	@Override
	public GameResult getGameResult() {
		return this.gameResult;
	}
	
	@Override
	public ArrayList<String> getFoundWords() {
		return this.foundWords;
	}

	/* IMPLEMENTATION OF BoardDatabaseInterface */

	@Override
	public long getID() throws BoardIdNotSetException {
		if (this.hasLegalID())
			return this.id;
		throw new BoardIdNotSetException("Board ID not yet set.");
	}

	@Override
	public boolean hasLegalID() {
		return this.id >= 0;
	}

	@Override
	public void setID(long id) throws BoardIdAlreadySetException {
		if (this.hasLegalID() && this.id == id)
			throw new BoardIdAlreadySetException("Stored Board ID is "
					+ this.id + "new ID would be " + id);
		this.id = id;
		this.gameResult.setBoardID(this.id);
	}

	@Override
	public String getSeed() {
		return SeedGenerator.Instance.generateSeedFromBoard(this);
	}

	/* END OF INTERFACE IMPLEMENTATIONS */

	private void updateScore(String string) {
		try {
			Letter[] sequence = Letter.getLetterSequence(string);
			int total = 0;
			for (Letter letter : sequence) {
				total += letter.getValue();
			}
			this.gameResult.addWord(total);
		} catch (InvalidLetterException ex) {
			Log.e("Board", ex.getMessage());
		}
	}

}
