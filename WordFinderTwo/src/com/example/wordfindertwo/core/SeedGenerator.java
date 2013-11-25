package com.example.wordfindertwo.core;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

import com.example.wordfindertwo.StandardDictionary;
import com.example.wordfindertwo.core.board.Board;

public enum SeedGenerator {

	Instance;

	public final static int MAX_ATTEMPTS = 1000;
	public final static char SEED_SECTION_DELIMITER = '%';

	private Random rand;

	private SeedGenerator() {
		this.rand = new Random();
	}

	/**
	 * creates a randomly generated seed from the two given dictionaries. Seed
	 * generation from just one dictionary is possible. In that case the
	 * dictionary should be given as the 'secondary' dictionary and the
	 * 'primary' dictionary should hold a <tt>null</tt> value. Seed generation
	 * from no dictionaries (both <tt>null</tt>) will lead to a completely
	 * random letter positioning and to an unplayable board since there are no
	 * words placed. The generated seed contains the board matrix configuration
	 * and all the words that are known to be in the board. Note that these
	 * words are just the ones that are actively placed during generation and
	 * not randomly produced ones.
	 * 
	 * @param primary
	 *            the primary dictionary for the seed generation. This
	 *            dictionary is used first during seed generation. This can be
	 *            <tt>null</tt> when a only the standard dictionary is used.
	 * @param secondary
	 *            the secondary dictionary for the board generation. This
	 *            dictionary is used in the second iteration of seed generation.
	 *            This should be the standard dictionary.
	 * @param boardSize
	 *            the size of the generated board. Should be 6.
	 * @return the generated seed
	 */
	public String generateRandomSeed(ArrayList<String> customs, int systemID,
			int boardSize) {
		// list with all words placed in the matrix
		ArrayList<String> words = new ArrayList<String>();
		char[][] matrix = new char[boardSize][boardSize];

		workThrough(customs, matrix, words);
		workThrough(StandardDictionary.getDictionary(systemID).getWords(),
				matrix, words);
		// fill empty fields randomly
		for (int y = 0; y < boardSize; y++) {
			for (int x = 0; x < boardSize; x++) {
				if (matrix[x][y] == '\0') {
					// random letter between 'A' (65) and 'Z' (90)
					matrix[x][y] = (char) (65 + rand.nextInt(26));
				}
			}
		}
		// parse seed
		return this.parseIntoSeed(matrix, words, systemID, customs);
	}

	private void workThrough(ArrayList<String> dic, char[][] matrix,
			ArrayList<String> words) {
		Log.i("SeedGen", "Dic Size: " + dic.size());
		if (dic == null || dic.size() == 0) {
			return;
		}
		for (int attempts = 0; attempts < Math
				.min(MAX_ATTEMPTS, dic.size() * 2); attempts++) {
			String word = dic.get(this.rand.nextInt(dic.size()));
			if (words.contains(word))
				continue;
			if (this.placeWord(matrix, word.toCharArray())) {
				words.add(word);
			}
		}
	}

	/**
	 * Tries to place a word on the matrix
	 * 
	 * @param matrix
	 *            the matrix to modify with the word
	 * @param word
	 *            the word to be placed
	 * @return true when word was placed, false otherwise
	 */
	private boolean placeWord(char[][] matrix, char[] word) {
		char[][] backup = this.copyMatrix(matrix);
		ArrayList<Point> usedPoints = new ArrayList<Point>();
		for (int i = 0; i < word.length; i++) {
			char letter = word[i];
			ArrayList<Point> legals = BoardPointRetreiver.Instance
					.getGoodPoints(letter, matrix, usedPoints);
			if (legals.isEmpty()) {
				matrix = backup;
				return false;
			}
			Point position = legals.get(rand.nextInt(legals.size()));
			usedPoints.add(position);
			matrix[position.getX()][position.getY()] = letter;
		}
		// this point is only reached when word was completely placed
		return true;
	}

	/**
	 * Generates a board seed from the given board. Using the generated seed to
	 * create a new board using the BoardFactory generates an identical board.
	 */
	public String generateSeedFromBoard(Board board) {
		assert board != null;

		return this.parseIntoSeed(board.getCharMatrix(),
				board.getWordsInBoard(), board.getSystemDictionaryID(),
				board.getCustomWords());
	}

	private char[][] copyMatrix(char[][] matrix) {
		char[][] copy = new char[matrix.length][matrix.length];
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				copy[x][y] = matrix[x][y];
			}
		}
		return copy;
	}

	private String parseIntoSeed(char[][] matrix, ArrayList<String> words,
			int systemDictionaryID, ArrayList<String> customWords) {
		String seed = "";
		// convert 2D char matrix into string (row by row)
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				seed += matrix[x][y];
			}
		}
		// add all words to the seed
		seed += SEED_SECTION_DELIMITER
				+ DictionaryHelper.Instance.serialize(words);
		// add custom word list to seed
		seed += SEED_SECTION_DELIMITER
				+ DictionaryHelper.Instance.serialize(customWords);
		// add system dictionary id to seed
		seed += SEED_SECTION_DELIMITER + "" + systemDictionaryID;
		
		return seed;
	}
}
