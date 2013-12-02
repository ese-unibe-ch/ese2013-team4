package com.example.wordfindertwo.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.wordfindertwo.core.GameResult;

public class ScoreDataSource {

	// Database fields
	private SQLiteDatabase db;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseHelper.KEY_ID,
			DatabaseHelper.KEY_GAME_RESULT_BOARD_ID,
			DatabaseHelper.KEY_GAME_RESULT_BOARD_SCORE };

	public ScoreDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void deleteGameResult(GameResult game_result) {
		long id = game_result.getBoardID();
		System.out.println("Comment deleted with id: " + id);
		db.delete(DatabaseHelper.TABLE_GAME_RESULT, DatabaseHelper.KEY_ID
				+ " = " + id, null);
	}

	public List<GameResult> getAllScores() {
		List<GameResult> game_results = new ArrayList<GameResult>();

		Cursor cursor = db.query(DatabaseHelper.TABLE_GAME_RESULT, allColumns,
				null, null, null, null, null);

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

	public List<GameResult> getAllHighScores() {
		List<GameResult> game_results = getAllScores();
		List<GameResult> high_scores = new ArrayList<GameResult>();
		for (GameResult game_result : game_results) {
			if (containsGameresultOfThisBoard(game_result.getBoardID(), high_scores)) {
				for (GameResult old_result : high_scores) {
					if (game_result.getBoardID() == old_result.getBoardID() && game_result.compareTo(old_result) > 0) {
						high_scores.remove(old_result);
						high_scores.add(game_result);
					} else {

					}
				}
			}
			else {
				high_scores.add(game_result);
			}
		}
		return high_scores;
	}

	private boolean containsGameresultOfThisBoard(long id, List<GameResult> high_scores) {
		for (GameResult result : high_scores) {
			if (result.getBoardID() == id)
				return true;
		}
		return false;
	}

	private GameResult cursorToGameResult(Cursor c) {
		long id = c.getLong(c.getColumnIndex(DatabaseHelper.KEY_ID));
		return dbHelper.getGameResultById(id);
	}
}