package com.example.wordfindertwo;

import java.util.ArrayList;

import com.example.wordfindertwo.core.board.Board;
import com.example.wordfindertwo.core.BoardFactory;
import com.example.wordfindertwo.core.GameResult;
import com.example.wordfindertwo.customs.*;

import android.util.Log;
import android.os.Bundle;
import android.provider.UserDictionary.Words;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

public class Game extends Activity {

	public static Game game;
	// CONSTANTS
	private static final CharSequence QUIT_BUTTON_TEXT = "QUIT";
	private static final long INITIAL_TIMER_VALUE = 120000;
	// FIELDS
	private TextView timerView;
	private TextView foundWords;
	private boolean paused = false;
	private Board board;
	private LinearLayout layout;
	private Button bStart;
	private Button bPause;
	private TextView score;
	private boolean hasFinishedNaturally = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Log.i("Game", "onCreate");
		setContentView(R.layout.activity_game);
		game = this;
		timerView = (TextView) findViewById(R.id.timer);
		score = (TextView) findViewById(R.id.score);
		foundWords = (TextView) findViewById(R.id.stringWordsFound);
		ButtonListProvider.Instance.configureForGame(game);
		for (CustomButton btn : ButtonListProvider.Instance.getList()) {
			btn.setBackgroundResource(android.R.drawable.btn_default);
		}

		GameTimer.Instance.setup(INITIAL_TIMER_VALUE);

		// generate board
		Log.i("Game", "generate Board");
		try {
			board = BoardFactory.Instance.createBoard(this.getIntent());
			Log.d("Game.Board", "Word count = " + board.getTotalWordCount());
		} catch (Exception e) {
			Log.e("Game", "Board Factory Crashed", e);
			System.exit(0);
		}
		Log.i("Game", "create timer buttons");
		bStart = (Button) findViewById(R.id.starttimer);
		bPause = (Button) findViewById(R.id.pausetimer);

		// BUTTONS-----------------------------------------------------------------
		// creating START Button
		bStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (bStart.getText().equals(QUIT_BUTTON_TEXT)) {
					game.finish();
				} else {
					GameTimer.Instance.start();
					update();
				}
				bPause.setClickable(true);
				game.setListener();
				fillBoard();
				bStart.setText(QUIT_BUTTON_TEXT);
			}
		});
		// creating PAUSE Button
		bPause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				timerView.setText("PAUSED");
				startActivityForResult(new Intent(Game.this, Pause.class), 0);
			}
		});
		bPause.setClickable(false);
		// --------------------------------------------------------------------------

		layout = (LinearLayout) findViewById(R.id.gamespace);
		score.setText("" + board.getBoardScore());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public void finish() {
		GameTimer.Instance.stop();
		Intent intent;
		if (this.hasFinishedNaturally) {
			intent = new Intent(this, AfterGame.class);
			GameResult result = this.board.getGameResult();
			result.addTimeBonus(GameTimer.Instance.getTimeRemaining());
			intent.putExtra("GameResult", result.serialize());
		} else {
			intent = new Intent(this, MainMenu.class);
		}
		startActivity(intent);
		super.finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (paused == true) {
			GameTimer.Instance.resume();
			bStart.setText(QUIT_BUTTON_TEXT);
			bStart.setClickable(true);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		GameTimer.Instance.pause();
		paused = true;
		bStart.setClickable(false);
	}

	/**
	 * finish this activity when the pause return value is 1. ignore any other
	 * input.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1)
			finish();
	}

	public void update() {
		this.score.setText(this.board.getBoardScore() + "\n"
				+ this.board.getFoundWordCount() + " / "
				+ this.board.getTotalWordCount());
		ArrayList<String> wordsFound = this.board.getFoundWords();
		if (wordsFound.size() > 0) {
			String foundWordsContent = wordsFound.get(wordsFound.size() - 1);
			for (int i = wordsFound.size() - 2; i >= 0; i--) {
				foundWordsContent += "\n" + wordsFound.get(i);
			}
			this.foundWords.setText(foundWordsContent);
		}
		if (board.isCompleted()) {
			Log.i("Game", "board completed");
			this.hasFinishedNaturally = true;
			this.finish();
		}
	}

	private void fillBoard() {
		Log.i("Game", "fill buttons");
		for (int i = 0; i < 36; i++) {
			int x = i % 6, y = i / 6;
			CustomButton btn = ButtonListProvider.Instance.getButtonAtIndex(i);
			btn.setTextSize(14f);
			btn.setText("" + board.getCharAt(x, y));
			btn.setValueText("" + board.getValueAt(x, y));
		}
		Log.i("Game", "setup timer");
	}

	public void timerUpdated(long millisToGo) {
		this.timerView.setText("" + (millisToGo / 1000));
	}

	public void timerFinished() {
		this.timerView.setText("Time's up!");
		this.hasFinishedNaturally = true;
		this.finish();
	}

	private void setListener() {
		layout.setOnTouchListener(new CustomOnTouchListener(board, this));
	}
}
