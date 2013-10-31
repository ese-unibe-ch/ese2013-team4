package core.test;

import core.BoardFactory;
import core.board.Board;
import core.exceptions.BoardGenerationException;

public class BoardFactoryTest {

	public static void main(String[] args) throws BoardGenerationException {
		Board b = BoardFactory.createRandomBoard(null, new TestDictionary(), 6);
		for (int y = 0; y < b.getBoardSize(); y++) {
			for (int x = 0; x < b.getBoardSize(); x++) {
				System.out.print(b.getCharAt(x, y));
			}
			System.out.println();
		}
		System.out.println();
		for (String w : b.getWordsInBoard()) {
			System.out.println(w);
		}
		System.out.println("Total: " + b.getWordsInBoard().size());
	}

}
