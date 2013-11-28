package com.example.wordfindertwo.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.wordfindertwo.core.GameResult;

public class ScoreDataSource {

  // Database fields
  private SQLiteDatabase database;
  private DatabaseHelper dbHelper;
  private String[] allColumns = { DatabaseHelper.KEY_ID,
      DatabaseHelper.KEY_GAME_RESULT_BOARD_SEED, DatabaseHelper.KEY_GAME_RESULT_BOARD_SCORE };

  public ScoreDataSource(Context context) {
    dbHelper = new DatabaseHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }
  
  public GameResult createGameResult(String data, int score) {
	    ContentValues values = new ContentValues();
	    values.put(DatabaseHelper.KEY_GAME_RESULT_BOARD_SEED, data);
	    values.put(DatabaseHelper.KEY_GAME_RESULT_BOARD_SCORE, score);
	    long insertId = database.insert(DatabaseHelper.TABLE_GAME_RESULT, null,
	        values);
	    Cursor cursor = database.query(DatabaseHelper.TABLE_GAME_RESULT,
	        allColumns, DatabaseHelper.KEY_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    GameResult newGameResult = cursorToGameResult(cursor);
	    cursor.close();
	    return newGameResult;
	  }

  public void deleteGameResult(GameResult game_result) {
    long id = game_result.getBoardID();
    System.out.println("Comment deleted with id: " + id);
    database.delete(DatabaseHelper.TABLE_GAME_RESULT, DatabaseHelper.KEY_ID
        + " = " + id, null);
  }

  public List<GameResult> getAllScores() {
    List<GameResult> game_results = new ArrayList<GameResult>();

    Cursor cursor = database.query(DatabaseHelper.TABLE_GAME_RESULT,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      GameResult game_result = cursorToGameResult(cursor);
      game_results.add(game_result);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return game_results;
  }

  private GameResult cursorToGameResult(Cursor c) {
    GameResult game_result = new GameResult(c.getLong((c.getColumnIndex(DatabaseHelper.KEY_ID))),
    		(c.getString(c.getColumnIndex(DatabaseHelper.KEY_GAME_RESULT_BOARD_SEED))),
    		(c.getInt(c.getColumnIndex(DatabaseHelper.KEY_GAME_RESULT_BOARD_SCORE))),
    		(c.getString(c.getColumnIndex(DatabaseHelper.KEY_GAME_RESULT_BOARD_NAME))));
    return game_result;
  }
} 