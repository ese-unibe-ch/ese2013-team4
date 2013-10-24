package core;

import java.util.ArrayList;

public abstract class ADictionary implements IDictionary {

	private ArrayList<String> words;
	private String title;
	private int id;
	
	public ADictionary (String title, int id) {
		this.title = title;
		this.words = this.generateWordList(this.title);
		this.makeUpperCase();
		this.id = id;
	}
	
	/**
	 * Converts all Strings in the dictionary to Upper-Case.
	 * 
	 * Should be run whenever a word was added.
	 */
	private final void makeUpperCase() {
		for (int i = 0; i < this.words.size(); i++) {
			this.words.set(i, this.words.get(i).toUpperCase());
		}
	}
	
	@Override
	public ArrayList<String> getWords() {
		return this.words;
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
	public abstract ArrayList<String> generateWordList (String title);

}
