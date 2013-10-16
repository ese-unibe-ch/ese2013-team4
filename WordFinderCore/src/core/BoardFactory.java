package core;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

public class BoardFactory {

	public static IBoard createBoard (IDictionary primary, IDictionary secondary, int boardSize) {
		//TODO: IMPLEMENT BOARD CREATION
		return null;
	}
	
	/**
	 * @return A recreation of a stored board with a given seed
	 */
	public static IBoard recreateBoard (IDictionary primary, IDictionary secondary, int boardSize, String seed) {
		//TODO: IMPLEMENT BOARD RECREATION
		return null;
	}
	/**
	 * Demo board 6x6
	 */
	public static IBoard createDemoBoard () {
		ILetterField[][] matrix = new ILetterField[6][6];
		matrix[0][0] = new LetterField(Letter.O, 0, 0);
		matrix[1][0] = new LetterField(Letter.B, 1, 0);
		matrix[2][0] = new LetterField(Letter.N, 2, 0);
		matrix[3][0] = new LetterField(Letter.E, 3, 0);
		matrix[4][0] = new LetterField(Letter.P, 4, 0);
		matrix[5][0] = new LetterField(Letter.A, 5, 0);
		matrix[0][1] = new LetterField(Letter.X, 0, 1);
		matrix[1][1] = new LetterField(Letter.H, 1, 1);
		matrix[2][1] = new LetterField(Letter.O, 2, 1);
		matrix[3][1] = new LetterField(Letter.T, 3, 1);
		matrix[4][1] = new LetterField(Letter.W, 4, 1);
		matrix[5][1] = new LetterField(Letter.P, 5, 1);
		matrix[0][2] = new LetterField(Letter.P, 0, 2);
		matrix[1][2] = new LetterField(Letter.E, 1, 2);
		matrix[2][2] = new LetterField(Letter.R, 2, 2);
		matrix[3][2] = new LetterField(Letter.O, 3, 2);
		matrix[4][2] = new LetterField(Letter.E, 4, 2);
		matrix[5][2] = new LetterField(Letter.R, 5, 2);
		matrix[0][3] = new LetterField(Letter.T, 0, 3);
		matrix[1][3] = new LetterField(Letter.L, 1, 3);
		matrix[2][3] = new LetterField(Letter.C, 2, 3);
		matrix[3][3] = new LetterField(Letter.S, 3, 3);
		matrix[4][3] = new LetterField(Letter.L, 4, 3);
		matrix[5][3] = new LetterField(Letter.B, 5, 3);
		matrix[0][4] = new LetterField(Letter.E, 0, 4);
		matrix[1][4] = new LetterField(Letter.K, 1, 4);
		matrix[2][4] = new LetterField(Letter.T, 2, 4);
		matrix[3][4] = new LetterField(Letter.G, 3, 4);
		matrix[4][4] = new LetterField(Letter.S, 4, 4);
		matrix[5][4] = new LetterField(Letter.A, 5, 4);
		matrix[0][5] = new LetterField(Letter.R, 0, 5);
		matrix[1][5] = new LetterField(Letter.A, 1, 5);
		matrix[2][5] = new LetterField(Letter.E, 2, 5);
		matrix[3][5] = new LetterField(Letter.P, 3, 5);
		matrix[4][5] = new LetterField(Letter.S, 4, 5);
		matrix[5][5] = new LetterField(Letter.C, 5, 5);
		
		ArrayList<IDictionary> dicts = new ArrayList<IDictionary>();
		dicts.add(new TestDictionary());
		return new Board(matrix, 6, dicts);
	}
}
