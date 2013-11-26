package com.example.wordfindertwo.core;

import java.util.ArrayList;

import android.content.Intent;
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
	 */
	private Board createRandomBoard(IDictionary customDictionary,
			int sytemDictionaryID) throws BoardGenerationException {
		String seed = SeedGenerator.Instance.generateRandomSeed(
				customDictionary != null ? customDictionary.getWords()
						: new ArrayList<String>(), sytemDictionaryID, 6);
		return this.createBoardFromSeed(-1, seed);
	}

	/**
	 * creates a board from the given seed and the two given dictionaries. When
	 * no dictionaries are given, the board is still playable, but does only
	 * accept words that were placed during initial board generation and are
	 * therefore contained in the seed string.
	 */
	private Board createBoardFromSeed(long boardID, String seed)
			throws BoardGenerationException {
		try {
			String[] fragments = seed.split(""
					+ SeedGenerator.SEED_SECTION_DELIMITER);
			String seedString = fragments[0];
			int boardSize = (int) Math.sqrt(seedString.length());
			ILetterField[][] matrix = new ILetterField[boardSize][boardSize];
			try {
				for (int i = 0; i < boardSize * boardSize; i++) {
					matrix[i % boardSize][i / boardSize] = new LetterField(
							Letter.getLetter(seedString.charAt(i)), i
									% boardSize, i / boardSize);
				}
			} catch (InvalidLetterException e) {
				throw new BoardGenerationException(
						"Invalid Char - unable to parse Board");
			}
			ArrayList<String> wordsInBoard = DictionaryHelper.Instance
					.deserialize(fragments[1]);
			ArrayList<String> customWords = DictionaryHelper.Instance
					.deserialize(fragments[2]);
			int sytemDicID = Integer.parseInt(fragments[3]);
			return new Board(matrix, customWords, sytemDicID, wordsInBoard);
		} catch (Exception e) {
			// TODO: replace with more sensible error handling.
			throw new BoardGenerationException();
		}
	}

	/**
	 * Generate a board from the Intent. Recreates a given board when the Intent
	 * carries the extras "BoardID" (int) and "BoardData" (String). In case the
	 * extras are not present, or are unable to be parsed, a random board will
	 * be generated.
	 * 
	 * @param intent
	 *            the Game Activity's Intent. Used to extract the Board
	 *            Generation data.
	 * @return the generated board, ready for game-play.
	 * @throws BoardGenerationException
	 *             in case of any errors during Board Generation. Should never
	 *             be thrown.
	 */
	public Board createBoard(Intent intent) throws BoardGenerationException {
		if (intent.hasExtra("BoardData") && intent.hasExtra("BoardID")) {
			/*
			 * RECREATE OLD BOARD.
			 * 
			 * NOTE: To recreate an old board both, "BoardID" and "BoardData"
			 * have to be filled. "BoardData" has to be filled with
			 * SeedGenerator-compatible data. otherwise a random board will be
			 * created
			 */
			try {
				Log.i("BoardFactory", "recreating board " + intent.getIntExtra("BoardID", -1));
				Log.i("BoardFactory", intent.getStringExtra("BoardData"));
				Board brd = this.createBoardFromSeed(
						intent.getIntExtra("BoardID", -1),
						intent.getStringExtra("BoardData"));
				Log.i("BoardFactory", "board recreation finished");
				return brd;
			} catch (BoardGenerationException e) {
				Log.e("BoardFactory", "board recreation failed");
				// TODO: implement more sensible error handling
			}
		}
		Log.i("BoardFactory", "creating random board");
		/*
		 * CREATE NEW BOARD
		 * 
		 * NOTE: the current implementation simply generates generic ENGLISH
		 * boards. has to be adapted to support other languages / custom word
		 * lists
		 */
		Board brd = this.createRandomBoard(null, 0);
		Log.i("BoardFactory", "board generation finished");
		Log.i("BoardFactory", brd.getSeed());
		return brd;
	}
}
