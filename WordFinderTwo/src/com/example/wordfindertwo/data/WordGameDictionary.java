package com.example.wordfindertwo.data;

import java.util.ArrayList;

import com.example.wordfindertwo.core.IDictionary;

public class WordGameDictionary implements IDictionary {

	String name;
	int id;
	ArrayList<String> words;

	@Override
	public void setName(String string) {
		this.name = string;
	}

	@Override
	public void setID(int id) {
		this.id = id;
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
	public int getID() {
		return this.id;
	}

}
