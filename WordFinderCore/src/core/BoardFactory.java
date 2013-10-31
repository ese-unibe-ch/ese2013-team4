package core;

import core.board.Board;
import core.exceptions.BoardGenerationException;
import core.exceptions.InvalidLetterException;
import core.test.TestDictionary;

public class BoardFactory {
	
	public static WordFinder wrdf = WordFinder.getInstance();

	public static Board createRandomBoard (IDictionary primary, IDictionary secondary, int boardSize) throws BoardGenerationException {
		String seed = SeedGenerator.getInstance().generateRandomSeed(primary, secondary, boardSize);
		return createBoardFromSeed (primary, secondary, boardSize, seed);
	}
	
	public static Board createBoardFromSeed (IDictionary primary, IDictionary secondary, int boardSize, String seed) throws BoardGenerationException {
		ILetterField[][] matrix = new ILetterField[boardSize][boardSize];
		try {
		for (int i = 0; i < boardSize * boardSize; i++) {
				matrix[i % boardSize][i / boardSize] = new LetterField(Letter.getLetter(seed.charAt(i)), i % boardSize, i / boardSize);
		}
		} catch (InvalidLetterException e) {
			throw new BoardGenerationException("Invalid Char - unable to parse Board");
		}
		Board brd = new Board(matrix, primary, secondary);
		brd.setWordsInBoard(wrdf.getWords(brd));
		return brd;
	}
	
	/**
	 * Demo board 6x6
	 */
	public static Board createDemoBoard () throws BoardGenerationException {
		
		String seed = "OBNEPAXHOTWPPEROERTLCSLBEKTGSARAEPSC";
		
		return createBoardFromSeed (null, new TestDictionary(), 6, seed);
	}
}
