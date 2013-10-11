package core;

public interface BoardInterface {

	public LetterFieldInterface[][] getMatrix();
	
	public int getSize();
	
	/**
	 * @param x horizontal coordinate of the letter (0: left)
	 * @param y vertical coordinate of the letter (0: top)
	 */
	public LetterFieldInterface getSquare(int x, int y);
	
}
