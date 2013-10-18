package core.gen;

import static org.junit.Assert.*;

import org.junit.Test;

import core.BoardFactory;
import core.IBoard;

public class BasicSeedGeneratorTest {

	@Test
	public void testSeedFromBoard() {
		ISeedGenerator seeder = new BasicSeedGenerator();
		String seed = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJ";
		IBoard board = BoardFactory.createBoardFromSeed(null, null, 6, seed);
		assertEquals(seed, seeder.generateSeedFromBoard(board));
	}

}
