package com.example.wordfindertwo.core;

import java.util.ArrayList;

import android.util.Log;

import com.example.wordfindertwo.core.board.Board;
import com.example.wordfindertwo.core.exceptions.BoardGenerationException;
import com.example.wordfindertwo.core.exceptions.InvalidLetterException;

public enum BoardFactory {

	Instance;

	/**
	 * creates a randomly generated Board from the two given dictionaries. Board
	 * generation from just one dictionary is possible. In that case the
	 * dictionary should be given as the 'secondary' dictionary and the
	 * 'primary' dictionary should hold a <tt>null</tt> value. Board generation
	 * from no dictionaries (both <tt>null</tt>) will lead to a completely
	 * random letter positioning and to an unplayable board since there are no
	 * words placed.
	 * 
	 * @param primary
	 *            the primary dictionary for the board generation. This
	 *            dictionary is used first during board generation. This can be
	 *            <tt>null</tt> when a only the standard dictionary is used.
	 * @param secondary
	 *            the secondary dictionary for the board generation. This
	 *            dictionary is used in the second iteration of board
	 *            generation. This should be the standard dictionary.
	 * @param boardSize
	 *            the size of the generated board. Should be 6.
	 * @return the generated Board
	 * @throws BoardGenerationException
	 *             in case of any exception during board generation. Such an
	 *             exception signal an error in the core of the game.
	 */
	public Board createRandomBoard(IDictionary primary, IDictionary secondary,
			int boardSize) throws BoardGenerationException {
		String seed = SeedGenerator.Instance.generateRandomSeed(primary,
				secondary, boardSize);
		return this.createBoardFromSeed(primary, secondary, seed);
	}

	/**
	 * creates a board from the given seed and the two given dictionaries. When
	 * no dictionaries are given, the board is still playable, but does only
	 * accept words that were placed during initial board generation and are
	 * therefore contained in the seed string.
	 */
	public Board createBoardFromSeed(IDictionary primary,
			IDictionary secondary, String seed) throws BoardGenerationException {
		String[] fragments = seed.split(""
				+ SeedGenerator.SEED_SECTION_DELIMITER);
		String seedString = fragments[0];
		int boardSize = (int) Math.sqrt(seedString.length());
		ILetterField[][] matrix = new ILetterField[boardSize][boardSize];
		try {
			for (int i = 0; i < boardSize * boardSize; i++) {
				matrix[i % boardSize][i / boardSize] = new LetterField(
						Letter.getLetter(seedString.charAt(i)), i % boardSize,
						i / boardSize);
			}
		} catch (InvalidLetterException e) {
			throw new BoardGenerationException(
					"Invalid Char - unable to parse Board");
		}
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 1; i < fragments.length; i++) {
			words.add(fragments[i]);
			Log.i("BoardFactory", fragments[i]);
		}
		return new Board(matrix, primary, secondary, words);
	}
}
