package com.example.wordfindertwo;

import com.example.wordfindertwo.core.board.Board;
import com.example.wordfindertwo.core.test.TestDictionary;
import com.example.wordfindertwo.core.BoardFactory;
import com.example.wordfindertwo.core.GameResult;
import com.example.wordfindertwo.customs.*;

import android.util.Log;
import android.os.Bundle;
import android.os.CountDownTimer;
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
	private static final long INITIAL_TIMER_VALUE = 60000;

	private long millisInFuture = INITIAL_TIMER_VALUE;
	private TextView timerView;
	private CountDownTimer timer;
	private boolean paused = false;
	private Board board;
	private LinearLayout layout;
	private Button bStart;
	private Button bPause;
	private boolean hasFinishedNaturally = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Log.i("Game", "onCreate");
		setContentView(R.layout.activity_game);
		game = this;
		timerView = (TextView) findViewById(R.id.timer);
		ButtonListProvider.Instance.configureForGame(game);
		for (CustomButton btn : ButtonListProvider.Instance.getList()) {
			btn.setBackgroundResource(android.R.drawable.btn_default);
		}

		// generate board
		Log.i("Game", "generate Board");
		try {
			board = BoardFactory.Instance.createRandomBoard(null,
					new TestDictionary(), 6);
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
				}
				restartTimer();
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
				timer.cancel();
				timerView.setText("PAUSED");
				startActivityForResult(new Intent(Game.this, Pause.class), 0);
			}
		});
		bPause.setClickable(false);
		// --------------------------------------------------------------------------

		layout = (LinearLayout) findViewById(R.id.gamespace);
		TextView score = (TextView) game.findViewById(R.id.score);
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
		Intent intent;
		if (this.hasFinishedNaturally) {
			intent = new Intent(this, AfterGame.class);
			GameResult result = this.board.getGameResult();
			result.addTimeBonus(this.millisInFuture);
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
			bStart.setText(QUIT_BUTTON_TEXT);
			bStart.setClickable(true);
			startTimer();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
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
			btn.setTextSize(12f);
			btn.setText("" + board.getCharAt(x, y) + " "
					+ board.getValueAt(x, y));
		}
		Log.i("Game", "setup timer");
	}

	private void startTimer() {
		// countdown atm 2min displayed in sec
		timer = new CountDownTimer(getMillisInFuture(), 1000) {

			// displays a new time every tick
			public void onTick(long millisUntilFinished) {
				TextView text = (TextView) findViewById(R.id.timer);
				text.setText("You have " + (millisUntilFinished / 1000)
						+ " sec remaining!");
				game.setMillisInFuture(millisUntilFinished);
			}

			public void onFinish() {
				TextView text = (TextView) findViewById(R.id.timer);
				text.setText("Time's up!");
				game.hasFinishedNaturally = true;
				game.finish();
			}
		};

		timer.start();
	}

	private void restartTimer() {
		this.millisInFuture = INITIAL_TIMER_VALUE;
		this.startTimer();
	}

	private long getMillisInFuture() {
		return millisInFuture;
	}

	private void setMillisInFuture(long value) {
		this.millisInFuture = value;
	}

	private void setListener() {
		layout.setOnTouchListener(new CustomOnTouchListener(board, this));
	}
}
