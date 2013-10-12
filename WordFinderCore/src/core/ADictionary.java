package core;

import java.util.ArrayList;

public abstract class ADictionary implements IDictionary {

	private ArrayList<String> words;
	private String title;
	private int id;
	
	public ADictionary (String title, int id) {
		this.title = title;
		//TODO make all words upper-case to ensure matching...
		this.words = this.generateWordList(this.title);
		this.id = id;
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
