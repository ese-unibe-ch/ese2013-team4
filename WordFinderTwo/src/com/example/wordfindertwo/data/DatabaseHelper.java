package com.example.wordfindertwo.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	// Fixed Board Size
	private static final int BOARD_SIZE = 6;
	// Database tag
	private static final String LOG = "DatabaseHelper";

	// Database Version ( Increased when we rewrite the Database structure in an upcoming version of the game )
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_NAME = "savingManager";
	
	// Table Names
	private static final String TABLE_BOARD = "boards";
	private static final String TABLE_SCORE = "scores";
	private static final String TABLE_DICTIONARY = "dictionaries";
	private static final String TABLE_DEDICATED_DICTIONARY = "dedct_dict";
	
	// Common column names
	private static final String KEY_ID = "id";
	
	// Boards Table - column names
	private static final String KEY_LETTERS = "letters";
	// Custom Dictionary muss noch besprochen werden
	// private static final String KEY_CUSTOM_WORDS = "words";
	
	// Score Table - column names
	private static final String KEY_SCORE_BOARD_ID = "board_id";
	private static final String KEY_SCORE_VALUE = "score_value";
	
	// Dictionary Table - column names
	private static final String KEY_DICTIONARY_NAME = "name";
	private static final String KEY_DICTIONARY_WORDS = "words";
	
	// Dedicated Dictionary Table - column names
	private static final String KEY_DEDICATED_BOARD_ID = "board_id";
	private static final String KEY_DEDICATED_WORDS = "words";
	
	// Table Create Statements
	// Boards table create statement
	private static final String CREATE_TABLE_BOARD = "CREATE TABLE "
			+ TABLE_BOARD + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_LETTERS + " TEXT"
			+ ")";
	
	// Score table create statement
	private static final String CREATE_TABLE_SCORE = "CREATE TABLE "
			+ TABLE_SCORE + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_SCORE_BOARD_ID + " INTEGER,"
			+ KEY_SCORE_VALUE + " INTEGER"
			+ ")";

	// Dictionary table create statement
	private static final String CREATE_TABLE_DICTIONARY = "CREATE TABLE "
			+ TABLE_DICTIONARY + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_DICTIONARY_NAME + " TEXT, "
			+ KEY_DICTIONARY_WORDS + " TEXT" 
			+ ")";
	

	// Dedicated Boards table create statement
	private static final String CREATE_TABLE_DEDICATED_DICTIONARY = "CREATE TABLE "
			+ TABLE_DEDICATED_DICTIONARY + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_DEDICATED_BOARD_ID + " INTEGER,"
			+ KEY_DEDICATED_WORDS + " TEXT"
			+ ")";
	
	public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_BOARD);
		db.execSQL(CREATE_TABLE_SCORE);
		db.execSQL(CREATE_TABLE_DICTIONARY);
		db.execSQL(CREATE_TABLE_DEDICATED_DICTIONARY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOARD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEDICATED_DICTIONARY);
		
		// create new tables
		onCreate(db);

	}
	
	public long createDictionaryEntry(IDictionary dictionary) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_DICTIONARY_WORDS, convertArrayListToString(dictionary.getWords()));
	    values.put(KEY_DICTIONARY_NAME, dictionary.getName());
	    
	    long dictionaryId = db.insert(TABLE_DICTIONARY, null, values);
	    
	    return dictionaryId;
	}

	public List<IDictionary> getDitcionaryEntries() {
		List<IDictionary> dictionaries = new ArrayList<IDictionary>();
		String selectQuery = "SELECT * FROM " + TABLE_DICTIONARY;
		
		Log.e(LOG, selectQuery);
		
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(selectQuery, null);
		
	    // looping through all rows and adding to list
	    if (c.moveToFirst()) {
	        do {
	            IDictionary dict = new WordGameDictionary();
	            dict.setId(c.getInt((c.getColumnIndex(KEY_ID))));
	            dict.setName((c.getString(c.getColumnIndex(KEY_DICTIONARY_NAME))));
	            dict.setWords(convertStringToArrayList(c.getString(c.getColumnIndex(KEY_DICTIONARY_WORDS))));
	            // adding to list
	            dictionaries.add(dict);
	        } while (c.moveToNext());
	    }
	 
	    return dictionaries;
	}

	public long updateDictionaryEntry(IDictionary dictionary) {
		SQLiteDatabase db = this.getWritableDatabase();
		
	  	ContentValues values = new ContentValues();
	    values.put(KEY_DICTIONARY_NAME, dictionary.getName());
	    values.put(KEY_DICTIONARY_WORDS, convertArrayListToString(dictionary.getWords()));
	 
	    // updating row
	    return db.update(TABLE_DICTIONARY, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(dictionary.getId()) });
	}

	public void deleteDictionaryEntry(IDictionary dictionary) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_DICTIONARY, KEY_ID + " = ?",
	            new String[] { String.valueOf(dictionary.getId()) });
	}
	
	public long createBoardEntry(IBoard board) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_LETTERS, board.getLetters());
	    
	    long boardId = db.insert(TABLE_DICTIONARY, null, values);
	    
	    return boardId;
	}

	/*public List<IBoard> getBoardEntries() {
		List<IBoard> boards = new ArrayList<IBoard>();
		String selectQuery = "SELECT * FROM " + TABLE_BOARD;
		
		Log.e(LOG, selectQuery);
		
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(selectQuery, null);
		
	    // looping through all rows and adding to list
	    if (c.moveToFirst()) {
	        do {
	            IBoard board = new Board();
	            board.setId(c.getInt((c.getColumnIndex(KEY_ID))));
	            board.setName((c.getString(c.getColumnIndex(KEY_LETTERS))));
	            // adding to list
	            boards.add(board);
	        } while (c.moveToNext());
	    }
	 
	    return boards;
	}*/
	
	public long updateBoardEntry(IDictionary board) {
		SQLiteDatabase db = this.getWritableDatabase();
		
	  	ContentValues values = new ContentValues();
	    values.put(KEY_LETTERS, board.getName());
	 
	    // updating row
	    return db.update(TABLE_BOARD, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(board.getId()) });
	}

	public void deleteBoardEntry(IBoard board) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_BOARD, KEY_ID + " = ?",
	            new String[] { String.valueOf(board.getId()) });
	    this.deleteScoreEntriesByBoard(board);
	}
	
	public long createScoreEntry(IBoard board, int value) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_SCORE_BOARD_ID, board.getId());
	    values.put(KEY_SCORE_VALUE, value);
	    
	    long scoreId = db.insert(TABLE_SCORE, null, values);
	    
	    return scoreId;
	}
	
	// Todo: schauen wie Score ausgegeben werden soll hier ein Beispiel
	
	public List<Integer> getScoreEntriesByBoardId(int id) {
		List<Integer> scores = new ArrayList<Integer>();
		String selectQuery = "SELECT * FROM " + TABLE_SCORE + " WHERE";
		
		Log.e(LOG, selectQuery);
		
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(selectQuery, null);
		
	    // looping through all rows and adding to list
	    if (c.moveToFirst()) {
	        do {
	            Integer score = new Integer(c.getInt((c.getColumnIndex(KEY_ID))));
	            // adding to list
	            scores.add(score);
	        } while (c.moveToNext());
	    }
	 
	    return scores;
	}


	public void deleteScoreEntriesByBoard(IBoard board) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_SCORE, KEY_SCORE_BOARD_ID + " = ?",
	            new String[] { String.valueOf(board.getId()) });
	}
	
	
	
	public static String convertArrayListToString(ArrayList<String> array_list){
		String str = array_list.toString(); 
		str = (str).substring(1, str.length() - 1);
		Log.e(LOG, str);
	    return str;
	}
	public static ArrayList<String> convertStringToArrayList(String str){
	    String[] arr = str.split(",");
	    return new ArrayList<String>(Arrays.asList(arr));
	}
}