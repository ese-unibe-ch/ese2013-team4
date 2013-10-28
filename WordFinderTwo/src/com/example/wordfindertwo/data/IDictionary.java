package com.example.wordfindertwo.data;

import java.util.ArrayList;

public interface IDictionary {

	void setName(String string);

	void setId(int int1);

	void setWords(ArrayList<String> convertStringToArrayList);

	String getName();

	ArrayList<String> getWords();

	int getId();

}
