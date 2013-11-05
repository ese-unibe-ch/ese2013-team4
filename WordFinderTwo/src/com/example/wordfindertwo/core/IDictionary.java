package com.example.wordfindertwo.core;

import java.util.ArrayList;

public interface IDictionary {
	
	public String getName();
	
	public ArrayList<String> getWords();
	
	public int getID();
	
	public void setName(String name);
	
	public void setWords(ArrayList<String> words);
	
	public void setID(int id);
	
}
