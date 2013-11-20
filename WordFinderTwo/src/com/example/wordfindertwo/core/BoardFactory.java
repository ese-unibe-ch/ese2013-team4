package com.example.wordfindertwo.core;

import android.util.Log;

import com.example.wordfindertwo.core.board.Board;
import com.example.wordfindertwo.core.exceptions.BoardGenerationException;
import com.example.wordfindertwo.core.exceptions.InvalidLetterException;

public class BoardFactory {

	public static Board createRandomBoard (IDictionary primary, IDictionary secondary, int boardSize) throws BoardGenerationException {
		String seed = SeedGenerator.Instance.generateRandomSeed(primary, secondary, boardSize);
		return createBoardFromSeed (primary, secondary, boardSize, seed);
	}
	
	public static Board createBoardFromSeed (IDictionary primary, IDictionary secondary, int boardSize, String seed) throws BoardGenerationException {
		ILetterField[][] matrix = new ILetterField[boardSize][boardSize];
		try {
			for (int i = 0; i < boardSize * boardSize; i++) {
				matrix[i % boardSize][i / boardSize] = new LetterField(Letter.getLetter(seed.charAt(i)), i % boardSize, i / boardSize);
			}
		} catch (InvalidLetterException e) {
			throw new BoardGenerationException("Invalid Char - unable to parse Board");
		}
		for (int y = 0; y < 6; y++) {
			String line = "";
			for (int x = 0; x < 6; x++) {
				line += matrix[x][y].getLetter().getChar();
			}
			Log.d("BoardFactory.create", line);
		}
		return new Board(matrix, primary, secondary);
	}
}
