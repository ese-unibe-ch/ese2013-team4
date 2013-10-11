package core;

import java.util.ArrayList;

public abstract class ADictionary implements IDictionary {

	private ArrayList<String> words;
	private String title;
	private int id;
	
	public ADictionary (String title) {
		this.title = title;
		this.words = this.generateWordList(this.title);
	}
	
	@Override
	public boolean containsWord(String word) {
		return words.contains(word);
	}
	
	@Override
	public String getName() {
		return this.title;
	}
	
	@Override
	public int getID() {
		return this.id;
	}
	
	/**
	 * Creates an ArrayList of all the words in the dictionary.
	 * 
	 * Called by the constructor.
	 * 
	 * @return
	 */
	abstract ArrayList<String> generateWordList (String title);

}
