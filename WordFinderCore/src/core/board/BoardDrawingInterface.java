package core.board;

import core.Letter;

/**
 * Interface with all the methods needed to draw the board.
 * 
 * Contains methods for retrieving information about the different ILetterFields. 
 */
public interface BoardDrawingInterface {

	/**
	 * Returns the Letter at the given coordinates.
	 * 
	 * @param x x-coordinate of the letter. 0 is left, 5 is right
	 * @param y y-coordinate of the letter. 0 is top, 5 is bottom
	 * @return
	 */
	public Letter getLetterAt(int x, int y);
	
	/**
	 * Returns the character of the field at the given coordinates
	 * 
	 * @param x x-coordinate of the letter. 0 is left, 5 is right
	 * @param y y-coordinate of the letter. 0 is top, 5 is bottom
	 * @return
	 */
	public char getCharAt(int x, int y);
	
	/**
	 * Returns the numerical value of the field at the given coordinates
	 * 
	 * @param x x-coordinate of the letter. 0 is left, 5 is right
	 * @param y y-coordinate of the letter. 0 is top, 5 is bottom
	 * @return
	 */
	public int getValueAt(int x, int y);
	
	public int getBoardSize();
}
