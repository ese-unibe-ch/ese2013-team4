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

import com.example.wordfindertwo.core.GameResult;
import com.example.wordfindertwo.core.IDictionary;

// Tip: use com.example.wordfindertwo.core.board.BoardDatabaseInterface for ID / Name / Seed operations. - andreas

public class DatabaseHelper extends SQLiteOpenHelper {
	
	// Database tag
	private static final String LOG = "DatabaseHelper";

	// Database Version ( Increased when we rewrite the Database structure in an
	// upcoming version of the game )
	private static final int DATABASE_VERSION = 8;

	private static final String DATABASE_NAME = "savingManager";

	// Table Names
	private static final String TABLE_BOARD = "boards";
	private static final String TABLE_DICTIONARY = "dictionaries";
	public static final String TABLE_GAME_RESULT = "game_result";
	public static final String TABLE_USER = "user";

	// Common column names
	public static final String KEY_ID = "id";

	// Boards Table - column names
	static final String KEY_BOARD_SEED = "seed";
	private static final String KEY_BOARD_NAME = "name";

	// Custom Dictionary muss noch besprochen werden
	// private static final String KEY_CUSTOM_WORDS = "words";

	// Dictionary Table - column names
	private static final String KEY_DICTIONARY_NAME = "name";
	private static final String KEY_DICTIONARY_WORDS = "words";

	// Game Results Table - column names
	public static final String KEY_GAME_RESULT_BOARD_ID = "board_id";
	public static final String KEY_GAME_RESULT_BOARD_SCORE = "score";
	public static final String KEY_GAME_RESULT_BOARD_USER = "user";

	
	// Ulser Table - column names
	public static final String KEY_USER_ONLINE_ID = "online_id";
	public static final String KEY_USER_NAME = "name";

	// Table Create Statements
	// Boards table create statement
	private static final String CREATE_TABLE_BOARD = "CREATE TABLE "
			+ TABLE_BOARD + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_BOARD_NAME + " TEXT,"
			+ KEY_BOARD_SEED + " TEXT"
			+ ")";
	
	private static final String CREATE_TABLE_USER = "CREATE TABLE "
			+ TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_USER_NAME + " TEXT,"
			+ KEY_USER_ONLINE_ID + " TEXT"
			+ ")";

	// Dictionary table create statement
	private static final String CREATE_TABLE_DICTIONARY = "CREATE TABLE "
			+ TABLE_DICTIONARY + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_DICTIONARY_NAME + " TEXT, " + KEY_DICTIONARY_WORDS + " TEXT"
			+ ")";

	// Dedicated Boards table create statement
	private static final String CREATE_TABLE_GAME_RESULT = "CREATE TABLE "
			+ TABLE_GAME_RESULT + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_GAME_RESULT_BOARD_ID + " TEXT,"
			+ KEY_GAME_RESULT_BOARD_SCORE + " INTEGER"
			+ ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_BOARD);
		db.execSQL(CREATE_TABLE_DICTIONARY);
		db.execSQL(CREATE_TABLE_GAME_RESULT);
		db.execSQL(CREATE_TABLE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOARD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_RESULT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);


		// create new tables
		onCreate(db);

	}

	public long createDictionaryEntry(IDictionary dictionary) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DICTIONARY_WORDS,
				convertArrayListToString(dictionary.getWords()));
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
				dict.setID(c.getInt((c.getColumnIndex(KEY_ID))));
				dict.setName((c.getString(c.getColumnIndex(KEY_DICTIONARY_NAME))));
				dict.setWords(convertStringToArrayList(c.getString(c
						.getColumnIndex(KEY_DICTIONARY_WORDS))));
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
		values.put(KEY_DICTIONARY_WORDS,
				convertArrayListToString(dictionary.getWords()));

		// updating row
		return db.update(TABLE_DICTIONARY, values, KEY_ID + " = ?",
				new String[] { String.valueOf(dictionary.getID()) });
	}

	public void deleteDictionaryEntry(IDictionary dictionary) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DICTIONARY, KEY_ID + " = ?",
				new String[] { String.valueOf(dictionary.getID()) });
	}

	public static String convertArrayListToString(ArrayList<String> array_list) {
		String str = array_list.toString();
		str = (str).substring(1, str.length() - 1);
		Log.e(LOG, str);
		return str;
	}

	public static ArrayList<String> convertStringToArrayList(String str) {
		String[] arr = str.split(",");
		return new ArrayList<String>(Arrays.asList(arr));
	}
	
	public long createGameResultEntry(GameResult game_result) {
		SQLiteDatabase db = this.getWritableDatabase();
		String board_id;
		if (game_result.getBoardID() == -1)
			board_id = createBoardEntry(game_result.getBoardData(), game_result.getName(), db);
		else
			board_id = Long.toString(game_result.getBoardID());
		
		ContentValues values = new ContentValues();
		values.put(KEY_GAME_RESULT_BOARD_ID, board_id);
		values.put(KEY_GAME_RESULT_BOARD_SCORE, game_result.getScore());
		long game_resultID = db.insert(TABLE_GAME_RESULT, null, values);
		db.close();
		return game_resultID;

	}

	private String createBoardEntry(String board_data, String board_name, SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		values.put(KEY_BOARD_SEED, board_data);
		values.put(KEY_BOARD_NAME, board_name);
		long game_resultID = db.insert(TABLE_BOARD, null, values);
		return Long.toString(game_resultID);
	}

	public GameResult getGameResultById(long game_result_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_GAME_RESULT, null, "id = " + game_result_id, null, null,
				null, null, null);
		if (c.moveToFirst()) {
			long board_id = c.getLong(c.getColumnIndex(KEY_GAME_RESULT_BOARD_ID));
			GameResult game_result = new GameResult(board_id, getBoardInfoById(KEY_BOARD_SEED, board_id, db) , (c.getInt(c
					.getColumnIndex(KEY_GAME_RESULT_BOARD_SCORE))),
					getBoardInfoById(KEY_BOARD_NAME, board_id, db));
			return game_result;
		}
		return null;
	}

	private String getBoardInfoById(String key, long board_id, SQLiteDatabase db) {
		Cursor c = db.query(TABLE_BOARD, null, "id = " + board_id, null, null,
				null, null, null);
		if (c.moveToFirst()) {
			return c.getString(c.getColumnIndex(key));
		}
		return null;
	}
	
	public String getPrimaryUserName() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_USER, null, "id = 1", null, null,
				null, null, null);
		if (c.moveToFirst()) {
			return c.getString(c.getColumnIndex(KEY_USER_NAME));
		}
		return null;
	}

}
