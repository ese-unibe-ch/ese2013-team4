package core.gen;

import java.util.ArrayList;
import java.util.Random;

import core.IBoard;
import core.IDictionary;
import core.ILetterField;

public class BasicSeedGenerator implements ISeedGenerator {

	public final static int MAX_ATTEMPTS = 1000;
	
	private Random rand;
	
	public BasicSeedGenerator () {
		this.rand = new Random();
	}
	
	@Override
	public String generateRandomSeed(IDictionary primary,
			IDictionary secondary, int boardSize) {
		
		assert secondary != null; //at least a secondary dictionary is required
		
		ArrayList<String> dic = (primary != null ? primary : secondary).getWords();
		
		char[][] matrix = new char[boardSize][boardSize];
		
		char[][] oldMatrix = this.copyMatrix(matrix);
		
		for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
			//STEP 1: select random word from dic
			char[] word = dic.get(this.rand.nextInt(dic.size())).toCharArray();
			//STEP 2: iterate through the word
			for (int i = 0; i < word.length; i++) {
				//STEP 2.1: get list of all good fields (empty or matching letter adjacent to last and not yet used in word / anywhere for first)
				
				//STEP 2.2.1: set letter on random field if there are any possibilities
				
				//STEP 2.2.2: revert to backup matrix if no possibilities
				
			}
		}
		
		//LAST STEP: fill empty fields with random chars
		
		//convert char matrix to seed string
		
		String seed = "";
		
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				seed += matrix[x][y];
			}
		}
		
		return seed;
	}

	@Override
	public String generateSeedFromBoard(IBoard board) {
		assert board != null;
		
		ILetterField[][] matrix = board.getMatrix();
		
		String seed = "";
		
		for (int y = 0; y < board.getSize(); y++) {
			for (int x = 0; x < board.getSize(); x++) {
				seed += matrix[x][y].getLetter().getChar();
			}
		}
		
		return seed;
	}
	
	private char[][] copyMatrix (char[][] matrix) {
		char[][] copy = new char[matrix.length][matrix.length];
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix.length; x++) {
				copy[x][y] = matrix[x][y];
			}
		}
		return copy;
	}

}
