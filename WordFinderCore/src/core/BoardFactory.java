package core;

import core.gen.*;
import java.util.ArrayList;

public class BoardFactory {

	public static IBoard createRandomBoard (IDictionary primary, IDictionary secondary, int boardSize) {
		String seed = SeedGenerator.getInstance().generateRandomSeed(primary, secondary, boardSize);
		return createBoardFromSeed (primary, secondary, boardSize, seed);
	}
	
	public static IBoard createBoardFromSeed (IDictionary primary, IDictionary secondary, int boardSize, String seed) {
		ArrayList<IDictionary> dics = new ArrayList<IDictionary>();
		dics.add(primary);
		dics.add(secondary);
		ILetterField[][] matrix = new ILetterField[boardSize][boardSize];
		for (int i = 0; i < boardSize * boardSize; i++) {
				matrix[i % boardSize][i / boardSize] = new LetterField(Letter.getLetter(seed.charAt(i)), i % boardSize, i / boardSize);
		}
		return new Board(matrix, boardSize, dics);
	}
	
	/**
	 * Demo board 6x6
	 */
	public static IBoard createDemoBoard () {
		
		String seed = "OBNEPAXHOTWPPEROERTLCSLBEKTGSARAEPSC";
		
		return createBoardFromSeed (null, new TestDictionary(), 6, seed);
	}
}
