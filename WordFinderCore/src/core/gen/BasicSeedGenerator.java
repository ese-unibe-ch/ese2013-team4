package core.gen;

import core.IBoard;
import core.IDictionary;
import core.ILetterField;

public class BasicSeedGenerator implements ISeedGenerator {

	@Override
	public String generateRandomSeed(IDictionary primary,
			IDictionary secondary, int boardSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateSeedFromBoard(IBoard board) {
		assert board != null;
		
		ILetterField[][] matrix = board.getMatrix();
		
		String seed = "";
		
		for (int y = 0; y < board.getSize(); y++) {
			for (int x = 0; x < board.getSize(); x++) {
				seed += matrix[x][y].getLetter().getChar();
			}
		}
		
		return seed;
	}

}
