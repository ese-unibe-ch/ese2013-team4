package core;

import java.util.ArrayList;

public interface WordInterface {

	public void setLetterSequence(ArrayList<Letter> letters);
	
	public ArrayList<Letter> getLetterSequence();
	
	/**
	 * @return string representation of the letter sequence from the word
	 */
	public String toString();
	
	public boolean equals(Object object);
	
	/**
	 * @return the combined value of the letters
	 */
	public int getWordValue();
	
}
