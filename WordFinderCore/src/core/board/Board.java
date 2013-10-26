package core.board;

import java.util.ArrayList;

import core.IDictionary;
import core.ILetterField;
import core.IWord;
import core.Letter;
import core.Point;
import core.SelectionStatus;
import core.Word;
import core.WordChecker;

public class Board implements BoardDictionarySupportInterface, BoardDrawingInterface, BoardInputInterface, BoardOperationInterface {

	private ILetterField[][] matrix;
	private final int boardSize;
	private IDictionary primary;
	private IDictionary secondary;
	
	private WordChecker checker;
	
	private ArrayList<ArrayList<Point>> foundWords;

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
		
		this.checker = WordChecker.getInstance();
		
		this.foundWords = new ArrayList<ArrayList<Point>>();
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
	
	@Override
	public int getBoardSize() {
		return this.boardSize;
	}
	
	/* IMPLEMENTATION OF BoardInputInterface */
	
	@Override
	public SelectionStatus submit(ArrayList<Point> sequence) {
		//STEP 1: check if sequence is legal (n adjacent to n-1 and no identicals).
		for (int i = 0; i < sequence.size(); i++) {
			if (sequence.lastIndexOf(sequence.get(i)) != i) //first != last => multiples
				return SelectionStatus.SelectionInvalid;
			if (i > 0)
				if ( ! sequence.get(i - 1).isAdjacent(sequence.get(i)))
					return SelectionStatus.SelectionInvalid;
		}
		//STEP 2: check if sequence is in Found list.
		if (!this.foundWords.isEmpty() && this.foundWords.contains(sequence)) //if it is in list, it is old, and of course a word, since only words are in list
			return SelectionStatus.SelectionOld;
		//STEP 3: convert to word
		ArrayList<Letter> wordSeq = new ArrayList<Letter>();
		for (Point point : sequence) {
			wordSeq.add(this.getLetterAt(point.getX(), point.getY()));
		}
		IWord word = new Word();
		word.setLetterSequence(wordSeq);
		//STEP 4: check if word
		if (this.checker.isValidWord(word, this)) {
			this.foundWords.add(sequence);
			return SelectionStatus.SelectionGood;
		}
		return SelectionStatus.SelectionBad;
	}
	
	/* IMPLEMENTATION OF BoardOperationInterface */
	
	@Override
	public char[][] getCharMatrix() {
		char[][] matrix = new char[this.boardSize][this.boardSize];
		for (int x = 0; x < this.boardSize; x++) {
			for (int y = 0; y < this.boardSize; y++) {
				matrix[x][y] = this.matrix[x][y].getLetter().getChar();
			}
		}
		return matrix;
	}
	
}
