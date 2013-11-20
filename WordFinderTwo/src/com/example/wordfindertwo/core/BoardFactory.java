package com.example.wordfindertwo.core;

import java.util.ArrayList;

import com.example.wordfindertwo.core.board.Board;
import com.example.wordfindertwo.core.exceptions.BoardGenerationException;
import com.example.wordfindertwo.core.exceptions.InvalidLetterException;

public enum BoardFactory {

	Instance;
	
	public Board createRandomBoard(IDictionary primary, IDictionary secondary, int boardSize) throws BoardGenerationException {
		String seed = SeedGenerator.Instance.generateRandomSeed(primary, secondary, boardSize);
		return this.createBoardFromSeed(primary, secondary, seed);
	}
	
	public Board createBoardFromSeed(IDictionary primary, IDictionary secondary, String seed) throws BoardGenerationException {
		String[] fragments = seed.split("" + SeedGenerator.SEED_SECTION_DELIMITER);
		String seedString = fragments[0];
		int boardSize = (int) Math.sqrt(seedString.length());
		ILetterField[][] matrix = new ILetterField[boardSize][boardSize];
		try {
			for (int i = 0; i < boardSize * boardSize; i++) {
				matrix[i % boardSize][i / boardSize] = new LetterField(Letter.getLetter(seedString.charAt(i)), i % boardSize, i / boardSize);
			}
		} catch (InvalidLetterException e) {
			throw new BoardGenerationException("Invalid Char - unable to parse Board");
		}
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < fragments.length; i++) {
			words.add(fragments[i]);
		}
		return new Board(matrix, primary, secondary, words);
	}
}
