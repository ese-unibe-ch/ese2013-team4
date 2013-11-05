package com.example.wordfindertwo.test;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.example.wordfindertwo.data.DatabaseHelper;
import com.example.wordfindertwo.data.IDictionary;
import com.example.wordfindertwo.data.WordGameDictionary;

public class DatabaseHelperTest extends AndroidTestCase {
	DatabaseHelper db;
	
	public void setUp(){
        RenamingDelegatingContext context 
        = new RenamingDelegatingContext(getContext(), "test_");
        db = new DatabaseHelper(context);
        
        /*
         * CREATE DICTIONARY ENTRIES
         */
        
        // Filling ArrayLists with words
		ArrayList<String> german_words = new ArrayList<String>();
		german_words.add("gaensebluemchen");
		german_words.add("krankenwagen");
		german_words.add("naturwissenschaften");
		
		ArrayList<String> english_words = new ArrayList<String>();;
		english_words.add("Flower");
		english_words.add("Trainstation");
		english_words.add("Science");
		
		// Creating Dictionaries
		IDictionary german = new WordGameDictionary();
		IDictionary english = new WordGameDictionary();
		
		// Put the ArrayLists inside the dictionaries
		german.setWords(german_words);
		english.setWords(english_words);
		
		// saving dictionaries to database and saving their ids (we don't need to save the id, i just do it for demonstration purposes)
		long german_words_id = db.createDictionaryEntry(german);
		long english_words_id = db.createDictionaryEntry(english);
		
		
    }
	
	public void testDeleteDictionaryEntry(){
		
				// Load the dictionaries from the database and assert if the number is right
				List<IDictionary> dictionaries = db.getDitcionaryEntries();
				assertSame("Wrong number of dictionary-entries in Database", 2, dictionaries.size());
				
				// Delete one of the dictionaries from the database.
				db.deleteDictionaryEntry(dictionaries.get(0));
				
				// Load the dictionaries from the database and assert if the number is right
				dictionaries = db.getDitcionaryEntries();
				assertSame("Wrong number of dictionary-entries in Database", 1, dictionaries.size());
				
    }
	
	public void testUpdateDictionaryEntry(){
		
		// Load the dictionaries from the database and assert if the number is right
		List<IDictionary> dictionaries = db.getDitcionaryEntries();
		assertSame("Wrong number of dictionary-entries in Database", 2, dictionaries.size());
		
		// Delete one of the dictionaries from the database.
		db.deleteDictionaryEntry(dictionaries.get(0));
		
		// Load the dictionaries from the database and assert if the number is right
		dictionaries = db.getDitcionaryEntries();
		assertSame("Wrong number of dictionary-entries in Database", 1, dictionaries.size());
		
}

    public void tearDown() throws Exception{
        db.close();
        super.tearDown();
    }

}
