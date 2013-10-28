package com.example.wordfindertwo.data;

import java.util.ArrayList;

public class WordGameDictionary implements IDictionary {

	String name;
	int id;
	ArrayList<String> words;
	
	@Override
	public void setName(String string) {
		this.name = string;

	}

	@Override
	public void setId(int int1) {
		this.id = int1;

	}

	@Override
	public void setWords(ArrayList<String> words) {
		this.words = words;

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
	public int getId() {
		return this.id;
	}

}
