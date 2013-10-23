package core.gen;

import static org.junit.Assert.*;

import org.junit.Test;

import core.BoardFactory;
import core.IBoard;

public class SeedGeneratorTest {

	@Test
	public void testSeedFromBoard() {
		SeedGenerator seeder = SeedGenerator.getInstance();
		String seed = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJ";
		IBoard board = BoardFactory.createBoardFromSeed(null, null, 6, seed);
		assertEquals(seed, seeder.generateSeedFromBoard(board));
	}

}
