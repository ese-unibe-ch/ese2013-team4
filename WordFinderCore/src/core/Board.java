package core;

import java.util.ArrayList;

public class Board implements IBoard {

	private ILetterField[][] matrix;
	private final int boardSize;
	private ArrayList<IDictionary> dictionaries;
	private ArrayList<ILetterField> sequence;

	public Board(ILetterField[][] matrix, int boardSize,
			ArrayList<IDictionary> dictionaries) {
		assert matrix.length == boardSize;
		for (int i = 0; i < matrix.length; i++) {
			assert matrix[i].length == boardSize;
		}
		assert !dictionaries.isEmpty();

		this.matrix = matrix;
		this.boardSize = boardSize;
		this.dictionaries = dictionaries;

		this.sequence = new ArrayList<ILetterField>();
	}

	@Override
	public ILetterField[][] getMatrix() {
		return this.matrix;
	}

	@Override
	public int getSize() {
		return this.boardSize;
	}

	@Override
	public ILetterField getSquare(int x, int y) {
		assert x >= 0 && x < boardSize;
		assert y >= 0 && y < boardSize;
		return this.matrix[x][y];
	}

	@Override
	public ArrayList<IDictionary> getDictionaries() {
		return this.dictionaries;
	}

	@Override
	public void toggle(int x, int y) {
		assert x >= 0 && x < boardSize;
		assert y >= 0 && y < boardSize;
		ILetterField square = this.getSquare(x, y);
		if (square.isSelected() && this.sequence.contains(square)) {
			// REMOVE ALL LETTERS AFTER THE ONE THAT WAS UNSELECTED
			int index = this.sequence.indexOf(square);
			for (int i = this.sequence.size() - 1; i >= index; i--) {
				// remove all letters after the unselected one and unselect them
				this.sequence.remove(i).unselect();
			}
		} else if (!square.isSelected() && square.isAdjacent(this.sequence.get(this.sequence.size() - 1))) {
			//ADD TO SEQUENCE AND SELECT
			square.select();
			this.sequence.add(square);
		} else {
			for (int i = this.sequence.size() - 1; i >= 0; i--) {
				this.sequence.remove(i).unselect();
			}
			square.select();
			this.sequence.add(square);
		}
	}

	@Override
	public IWord getSelectedSequence() {
		IWord word = new Word();
		ArrayList<Letter> letters = new ArrayList<Letter>();
		for (ILetterField field : this.sequence) {
			letters.add(field.getLetter());
		}
		word.setLetterSequence(letters);
		return word;
	}

	@Override
	public void unselectAll() {
		for (int i = this.sequence.size() - 1; i >= 0; i--) {
			this.sequence.remove(i).unselect();
		}
	}

}
