package gen;

import core.IBoard;
import core.IDictionary;

public interface ISeedGenerator {

	public String generateRandomSeed(IDictionary primary, IDictionary secondary, int boardSize);
	
	public String generateSeedFromBoard(IBoard board);
	
}
