package com.example.wordfindertwo.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.example.wordfindertwo.core.BoardFactory;
import com.example.wordfindertwo.core.GameResult;
import com.example.wordfindertwo.core.IDictionary;
import com.example.wordfindertwo.core.SeedGenerator;
import com.example.wordfindertwo.core.exceptions.BoardGenerationException;
import com.example.wordfindertwo.data.DatabaseHelper;
import com.example.wordfindertwo.data.WordGameDictionary;

public class DatabaseHelperTest extends AndroidTestCase {
	private DatabaseHelper db;
	private String boardSeed;
	private long boardID = -1;
	private Integer score = 150;
	private GameResult game_result_mock;
	private String boardData = "MIETPLQRDRAPKMEONGOMRDIAAIBAOIGVLPRP%LIBRARIAN¤PRODIGAL¤MIDTERM¤AMOK¤LANG¤IAN¤ARE¤GIN¤PADRE¤LIM¤ANA¤RODEO¤DRAIN¤RED¤TAO%%0";
	
	public void setUp() throws BoardGenerationException{
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
		
		ArrayList<String> english_words = new ArrayList<String>();
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
		
		// Set up Mockobject
		
		game_result_mock = mock(GameResult.class);
		when(game_result_mock.getBoardID()).thenReturn(boardID);
        when(game_result_mock.getScore()).thenReturn(score);
        when(game_result_mock.getBoardData()).thenReturn(boardData);
		
    }
	
	public void testLoadDictionaryEntry(){
		
		// Load the dictionaries from the database and assert if the number is right
		List<IDictionary> dictionaries = db.getDitcionaryEntries();
		assertEquals("Loaded Dictionary has Wrong ID", 1, dictionaries.get(0).getID());
		assertEquals("Loaded Dictionary has Wrong ID", 2, dictionaries.get(1).getID());
		assertSame("Wrong number of dictionary-entries in Database", 2, dictionaries.size());
		
		// Delete one of the dictionaries from the database.
		db.deleteDictionaryEntry(dictionaries.get(0));
		
		// Load the dictionaries from the database and assert if the number is right
		dictionaries = db.getDitcionaryEntries();
		assertSame("Wrong number of dictionary-entries in Database", 1, dictionaries.size());
    }
	
	public void testSaveGameResult() {
		db.createGameResultEntry(game_result_mock);
		GameResult loaded_game_result = db.getGameResultEntries().get(0);
		assertEquals("Failed loading right GameResult Object from Database", 1, loaded_game_result.getBoardID());
		assertEquals("Failed loading right GameResult Object from Database", game_result_mock.getScore(), loaded_game_result.getScore());
		assertEquals("Failed loading right GameResult Object from Database", game_result_mock.getBoardData(), loaded_game_result.getBoardData());
	}

    public void tearDown() throws Exception{
        db.close();
        super.tearDown();
    }

}