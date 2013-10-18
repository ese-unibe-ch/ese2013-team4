package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardFactoryTest {

	@Test
	public void testDicsCorrect() {
		IDictionary dic = new TestDictionary();
		IBoard board = BoardFactory.createBoardFromSeed(null, dic, 6,        "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJ");
		assertNull(board.getDictionaries().get(0)); //no primary
		assertEquals(board.getDictionaries().size(), 2); //exactly 2 dics
		assertEquals(board.getDictionaries().get(1), dic); //second ok
	}
	
	@Test
	public void testLetterPlacing() {
		IDictionary dic = new TestDictionary();
		IBoard board = BoardFactory.createBoardFromSeed(null, dic, 6,  "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJ");
		assertEquals(board.getSquare(0, 0).getLetter(), Letter.A);
		assertEquals(board.getSize(), 6);
		assertEquals(board.getSquare(1, 1).getLetter(), Letter.H);
	}

}
