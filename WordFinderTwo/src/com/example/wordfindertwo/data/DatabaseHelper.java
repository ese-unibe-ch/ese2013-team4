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
	// Fixed Board Size
	private static final int BOARD_SIZE = 6;
	// Database tag
	private static final String LOG = "DatabaseHelper";

	// Database Version ( Increased when we rewrite the Database structure in an
	// upcoming version of the game )
	private static final int DATABASE_VERSION = 3;

	private static final String DATABASE_NAME = "savingManager";

	// Table Names
	private static final String TABLE_BOARD = "boards";
	private static final String TABLE_SCORE = "scores";
	private static final String TABLE_DICTIONARY = "dictionaries";
	private static final String TABLE_DEDICATED_DICTIONARY = "dedct_dict";
	public static final String TABLE_GAME_RESULT = "game_result";

	// Common column names
	public static final String KEY_ID = "id";

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

	// Game Results Table - column names
	public static final String KEY_GAME_RESULT_BOARD_SEED = "seed";
	public static final String KEY_GAME_RESULT_BOARD_SCORE = "score";
	public static final String KEY_GAME_RESULT_BOARD_NAME = "name";

	// Table Create Statements
	// Boards table create statement
	private static final String CREATE_TABLE_BOARD = "CREATE TABLE "
			+ TABLE_BOARD + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_LETTERS + " TEXT" + ")";

	// Score table create statement
	private static final String CREATE_TABLE_SCORE = "CREATE TABLE "
			+ TABLE_SCORE + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_SCORE_BOARD_ID + " INTEGER," + KEY_SCORE_VALUE + " INTEGER"
			+ ")";

	// Dictionary table create statement
	private static final String CREATE_TABLE_DICTIONARY = "CREATE TABLE "
			+ TABLE_DICTIONARY + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_DICTIONARY_NAME + " TEXT, " + KEY_DICTIONARY_WORDS + " TEXT"
			+ ")";

	// Dedicated Boards table create statement
	private static final String CREATE_TABLE_DEDICATED_DICTIONARY = "CREATE TABLE "
			+ TABLE_DEDICATED_DICTIONARY
			+ "("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY, "
			+ KEY_DEDICATED_BOARD_ID
			+ " INTEGER,"
			+ KEY_DEDICATED_WORDS + " TEXT" + ")";

	// Dedicated Boards table create statement
	private static final String CREATE_TABLE_GAME_RESULT = "CREATE TABLE "
			+ TABLE_GAME_RESULT + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
			+ KEY_GAME_RESULT_BOARD_SEED + " TEXT,"
			+ KEY_GAME_RESULT_BOARD_SCORE + " INTEGER,"
			+ KEY_GAME_RESULT_BOARD_NAME + " TEXT" + ")";

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
		db.execSQL(CREATE_TABLE_GAME_RESULT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOARD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEDICATED_DICTIONARY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME_RESULT);

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
		assert (game_result.getBoardID() == -1);
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_GAME_RESULT_BOARD_SEED, game_result.getBoardData());
		values.put(KEY_GAME_RESULT_BOARD_SCORE, game_result.getScore());
		values.put(KEY_GAME_RESULT_BOARD_NAME, game_result.getName());
		long game_resultID = db.insert(TABLE_GAME_RESULT, null, values);

		return game_resultID;

	}

	public List<GameResult> getGameResultEntries() {
		List<GameResult> game_results = new ArrayList<GameResult>();
		String selectQuery = "SELECT * FROM " + TABLE_GAME_RESULT;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				GameResult game_result = new GameResult(
						c.getLong((c.getColumnIndex(KEY_ID))),
						(c.getString(c
								.getColumnIndex(KEY_GAME_RESULT_BOARD_SEED))),
						(c.getInt(c.getColumnIndex(KEY_GAME_RESULT_BOARD_SCORE))),
						(c.getString(c
								.getColumnIndex(KEY_GAME_RESULT_BOARD_NAME))));
				Log.i("DB", "Name: " + game_result.getName());
				// adding to list
				game_results.add(game_result);
			} while (c.moveToNext());
		}

		return game_results;
	}

	public GameResult getGameResultById(long id,
			GameResult game_result_to_be_safed) {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] args = { Long.toString(id) };
		Cursor c = db.query(TABLE_GAME_RESULT, null, "id = " + id, null, null,
				null, null, null);
		if (c.moveToFirst()) {
			GameResult game_result = new GameResult(c.getLong((c
					.getColumnIndex(KEY_ID))), (c.getString(c
					.getColumnIndex(KEY_GAME_RESULT_BOARD_SEED))), (c.getInt(c
					.getColumnIndex(KEY_GAME_RESULT_BOARD_SCORE))),
					(c.getString(c.getColumnIndex(KEY_GAME_RESULT_BOARD_NAME))));
			return game_result;
		}
		return null;
	}

	public void updateGameResult(GameResult game_result) {
		assert (game_result.getBoardID() != -1);
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_GAME_RESULT_BOARD_SEED, game_result.getBoardData());
		values.put(KEY_GAME_RESULT_BOARD_SCORE, game_result.getScore());
		values.put(KEY_GAME_RESULT_BOARD_NAME, game_result.getName());
		long game_resultID = db.update(TABLE_GAME_RESULT, values, "id = ?", new String[]{Long.toString(game_result.getBoardID())});
	}

}
