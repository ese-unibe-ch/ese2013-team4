package com.example.wordfindertwo.core.test;

import java.util.ArrayList;
import java.util.Locale;

import com.example.wordfindertwo.StandartDictionary;
import com.example.wordfindertwo.core.IDictionary;

/**
 * A basic dictionary used for testing.
 * 
 * Contains a list of a few English words.
 * 
 * @author Andreas Waelchli <andreas.waelchli@me.com>
 */
public class TestDictionary implements IDictionary {

	int id;
	String name;
	ArrayList<String> words;
	
	public TestDictionary() {
		this.id = 0;
		this.name = "Test Dictionary";
		this.words = this.generateWordList();
	}
	
	private final ArrayList<String> generateWordList() {
		ArrayList<String> words = new ArrayList<String>();
		// -- INSERT WORDS HERE --
		words = StandartDictionary.getEnglishList();
		// -- END OF WORD LIST --
		for (int i = 0; i < words.size(); i++) {
			words.set(i, words.get(i).toUpperCase(Locale.ENGLISH));
		}
		// END
		return words;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ArrayList<String> getWords() {
		return this.words;
	}

	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

	@Override
	public void setID(int id) {
		this.id = id;
	}

}
