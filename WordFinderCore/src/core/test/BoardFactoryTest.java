package core.test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.BoardFactory;
import core.board.Board;

public class BoardFactoryTest {

	public static void main(String[] args) {
		Board b = BoardFactory.createDemoBoard();
		for (int y = 0; y < b.getBoardSize(); y++) {
			for (int x = 0; x < b.getBoardSize(); x++) {
				System.out.print(b.getLetterAt(x, y).getChar());
			}
			System.out.println();
		}
		System.out.println();
		for (String w : b.getWordsInBoard()) {
			System.out.println(w);
		}
	}

}
