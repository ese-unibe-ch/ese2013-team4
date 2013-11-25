package com.example.wordfindertwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

import com.example.wordfindertwo.core.IDictionary;

import android.content.Context;

public enum StandardDictionary implements IDictionary {

	ENGLISH("English", 0, R.raw.english);
	
	String name;
	int id;
	ArrayList<String> words;

	StandardDictionary(String name, int id, int ref) {
		this.name = "Default Dictionary (English)";
		this.id = id;
		this.words = this.generateWordList(ref);
	}
	
	public static StandardDictionary getDictionary(int id) {
		for (StandardDictionary dic : values()) {
			if (dic.id == id)
				return dic;
		}
		return ENGLISH;
	}
	
	private final ArrayList<String> generateWordList(int ref) {
		Context context = MyApp.getContext();
		InputStream inputStream = context.getResources().openRawResource(ref);
		InputStreamReader inputreader = new InputStreamReader(inputStream);
		BufferedReader buffreader = new BufferedReader(inputreader);
		String line;
		ArrayList<String> list = new ArrayList<String>();
		try {
			while ((line = buffreader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).toUpperCase(Locale.ENGLISH));
		}

		return list;
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

	@Override
	public int getID() {
		return this.id;
	}

}
