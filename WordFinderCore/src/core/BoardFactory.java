package core;

import core.gen.*;

public class BoardFactory {
	
	private static ISeedGenerator seedGenerator = new BasicSeedGenerator();

	public static IBoard createRandomBoard (IDictionary primary, IDictionary secondary, int boardSize) {
		String seed = seedGenerator.generateRandomSeed(primary, secondary, boardSize);
		return createBoardFromSeed (primary, secondary, boardSize, seed);
	}
	
	/**
	 * @return a recreation of a stored board with a given seed
	 */
	public static IBoard createBoardFromSeed (IDictionary primary, IDictionary secondary, int boardSize, String seed) {
		//TODO: IMPLEMENT BOARD RECREATION
		return null;
	}
	
	public static String createSeedFromBoard (IBoard board) {
		return seedGenerator.generateSeedFromBoard(board);
	}
	
	/**
	 * Demo board 6x6
	 */
	public static IBoard createDemoBoard () {
		
		String seed = "OBNEPAXHOTWPPEROERTLCSLBEKTGSARAEPSC";
		
		return createBoardFromSeed (null, new TestDictionary(), 6, seed);
	}
}
