package com.example.wordfindertwo.core;

import java.util.ArrayList;

public enum DictionaryHelper {

	Instance;

	public final static char DICTIONARY_SERIAL_DELIMITER = '¤';

	public String serialize(ArrayList<String> dic) {
		if (dic != null && dic.size() > 0) {
			String serial = dic.get(0);
			for (int i = 1; i < dic.size(); i++) {
				serial += DICTIONARY_SERIAL_DELIMITER + dic.get(i);
			}
			return serial;
		} else {
			return "";
		}
	}

	public ArrayList<String> deserialize(String serial) {
		ArrayList<String> words = new ArrayList<String>();
		if (serial.equals("")) {
			return words;
		}
		String[] parts = serial.split("" + DICTIONARY_SERIAL_DELIMITER);
		for (String word : parts) {
			words.add(word);
		}
		return words;
	}
}
