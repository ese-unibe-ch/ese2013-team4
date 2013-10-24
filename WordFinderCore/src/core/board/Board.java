package core.board;

import java.util.ArrayList;
import core.IDictionary;
import core.ILetterField;

public class Board {

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
	
	
	
}
