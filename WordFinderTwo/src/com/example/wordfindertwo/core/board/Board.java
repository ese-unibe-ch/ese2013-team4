package com.example.wordfindertwo.core.board;

import java.util.ArrayList;

import android.util.Log;

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
	private IDictionary primary;
	private IDictionary secondary;
	private ArrayList<String> wordsInBoard;
	private ArrayList<ArrayList<Point>> foundWords;
	private long id;
	private GameResult gameResult;

	/**
	 * This constructor is meant for use by the BoardFactory, but can be used in
	 * any circumstance
	 * 
	 * @param matrix
	 *            the letter matrix for the board. This 2D array has to be
	 *            exactly square, i.e.
	 *            <tt>matrix.length == matrix[i].length, i=0..matrix.length-1</tt>
	 * @param primary
	 *            the primary dictionary of the board. this is the dictionary
	 *            that is selected by the user to play with. When the default
	 *            dictionary is used, this parameter should be <tt>null</tt>,
	 *            although this property is not required for operation, and
	 *            won't raise any exceptions if not respected.<br/>
	 *            All words of this dictionary are considered as valid words.
	 * @param secondary
	 *            the secondary dictionary for the board. this is the default
	 *            dictionary for the board (i.e. English system dictionary).<br/>
	 *            All words of this dictionary are also considered as valid
	 *            words.
	 */
	public Board(ILetterField[][] matrix, IDictionary primary,
			IDictionary secondary, ArrayList<String> wordsInBoard) {
		this.matrix = matrix;
		this.primary = primary;
		this.secondary = secondary;
		this.foundWords = new ArrayList<ArrayList<Point>>();
		this.wordsInBoard = wordsInBoard;
		this.id = -1;
		this.gameResult = new GameResult(this.id, this.getSeed());
	}

	public ILetterField[][] getMatrix() {
		return this.matrix;
	}

	/* IMPLEMENTATION OF BoardDictionarySupportInterface */

	@Override
	public IDictionary getPrimaryDictionary() {
		return this.hasPrimaryDictionary() ? this.primary : this.secondary;
	}

	@Override
	public IDictionary getSecondaryDictionary() {
		return this.secondary;
	}

	@Override
	public boolean hasPrimaryDictionary() {
		return this.primary != null;
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
		if (!this.foundWords.isEmpty() && this.foundWords.contains(sequence)) {
			Log.d("Board.submit", "Word " + word + " is already found");
			return SelectionStatus.SelectionOld;
		}
		// STEP 4: check string
		// if (this.wordsInBoard.contains(word)) {
		if (this.secondary.getWords().contains(word)
				|| (this.primary != null && this.primary.getWords().contains(
						word))) {
			this.foundWords.add(new ArrayList<Point>(sequence));
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

	/**
	 * @deprecated
	 */
	public String getEndData() {
		return this.getSeed() + "%" + this.getBoardScore();
	}

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
