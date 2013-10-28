package com.example.wordfindertwo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.wordfindertwo.R;
import com.example.wordfindertwo.R.layout;
import com.example.wordfindertwo.R.menu;
import com.example.wordfindertwo.data.DatabaseHelper;
import com.example.wordfindertwo.data.IDictionary;
import com.example.wordfindertwo.data.WordGameDictionary;

public class DatabaseDemonstration extends Activity {
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		
db = new DatabaseHelper(getApplicationContext());
		
		// Creating Dictionaries
		IDictionary german = new WordGameDictionary();
		IDictionary english = new WordGameDictionary();
		
		// Filling Lists with words
		ArrayList<String> german_words = new ArrayList<String>();
		german_words.add("gaensebluemchen");
		german_words.add("krankenwagen");
		german_words.add("naturwissenschaften");
		
		ArrayList<String> english_words = new ArrayList<String>();;
		english_words.add("Flower");
		english_words.add("Trainstation");
		english_words.add("Science");
		
		// Put wordlist inside the dictionaries
		
		german.setWords(german_words);
		english.setWords(english_words);
		
		
		// saving dictionaries and saving their ids
		long german_words_id = db.createDictionaryEntry(german);
		long english_words_id = db.createDictionaryEntry(english);
		
		// delete german
		db.deleteDictionaryEntry(german);
		
		// get it all
		List<IDictionary> dictionaries = db.getDitcionaryEntries();
		
		// update english dictionary (later)
		english_words.add("Whatever");
		english.setWords(english_words);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friend, menu);
		return true;
	}

}
