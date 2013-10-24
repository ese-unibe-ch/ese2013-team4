package core.board;

import java.util.ArrayList;
import core.IDictionary;
import core.ILetterField;
import core.Letter;

public class Board implements BoardDictionarySupportInterface, BoardDrawingInterface {

	private ILetterField[][] matrix;
	private final int boardSize;
	private IDictionary primary;
	private IDictionary secondary;

	public Board(ILetterField[][] matrix, int boardSize, IDictionary primary, IDictionary secondary) {
		assert matrix.length == boardSize;
		for (int i = 0; i < matrix.length; i++) {
			assert matrix[i].length == boardSize;
		}
		assert secondary != null;

		this.matrix = matrix;
		this.boardSize = boardSize;

		this.primary = primary;
		this.secondary = secondary;
		
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
	
	/* IMPLEMENTATION OF BoardDrawingInterface */
	
	@Override
	public Letter getLetterAt(int x, int y) {
		assert x >= 0 && x < this.boardSize && y >= 0 && y < this.boardSize;
		
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
	
}
