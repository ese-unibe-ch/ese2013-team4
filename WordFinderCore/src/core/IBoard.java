package core;

import java.util.ArrayList;

public interface IBoard {

	public ILetterField[][] getMatrix();
	
	public int getSize();
	
	/**
	 * @param x horizontal coordinate of the letter (0: left)
	 * @param y vertical coordinate of the letter (0: top)
	 */
	public ILetterField getSquare(int x, int y);

	public ArrayList<IDictionary> getDictionaries();
	
	/**
	 * Select the Letter at the given coordinate (x|y).
	 * 
	 * If there's no letter selected up to now, it creates a new sequence.
	 * If the Letter already is selected, it is unselected.
	 * 		All subsequent letters are unselected as well.
	 * If the Letter is not adjacent to the one selected before,
	 * 		it drops all previously selected letters and starts a new sequence
	 * 
	 * @param x horizontal coordinate of the letter (0: left)
	 * @param y vertical coordinate of the letter (0: top)
	 */
	public void toggle(int x, int y);
	
	/**
	 * returns a word object constructed out of the currently selected sequence.
	 * 
	 * @returns an empty word if there's no selection
	 */
	public IWord getSelectedSequence();
	
	/**
	 * clears the current selection
	 */
	public void unselectAll();
	
}
