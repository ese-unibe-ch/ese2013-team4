package com.example.wordfindertwo;

import com.example.wordfindertwo.R;
import com.example.wordfindertwo.core.board.Board;
import com.example.wordfindertwo.core.test.TestDictionary;
import com.example.wordfindertwo.core.BoardFactory;
import com.example.wordfindertwo.customs.CustomButton;
import com.example.wordfindertwo.customs.CustomOnTouchListener;

import android.util.Log;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.UserDictionary.Words;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import java.util.*;

public class Game extends Activity {
	
	// -------------timer Variables------------------
	public long millisInFuture = 60000;
	private TextView timerView;
	CountDownTimer timer;
	private Activity gameActivity = this;
	// -----------------------------------------------

	boolean paused = false;
	private Board board;
	LinearLayout layout; 
	static ArrayList<Character> word;
	Button bStart;
	Button bPause;
	private Intent intent;
	
	public static Game game;

	private TextView scrollWords;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Game", "onCreate");
		setContentView(R.layout.activity_game);
		game = this;
	
		//setting scrollView with words in board
		scrollWords = (TextView) findViewById(R.id.wordsInBoard);
		scrollWords.setText(board.toTextViewString());
		
		//setting timerView
		timerView = (TextView) findViewById(R.id.timer);	
		
		//instanciating buttonlistprovider
		ButtonListProvider blp = new ButtonListProvider(this);
		
		//setting btn_default background
		for (CustomButton btn : blp.getList()){
			btn.setBackgroundResource(android.R.drawable.btn_default);
		}
		
		if (blp.getButtonAt(0, 0) == null) {
			Log.i("Game", "ButtonListProvider is null");
		}
		
		//generating board
		Log.i("Game", "generate Board");
		try {
			board = BoardFactory.createRandomBoard(null, new TestDictionary(), 6);
			Log.d("Game.Board", "Word count = " + board.getWordsInBoard().size());
		} catch(Exception e) {
			Log.e("Game", "Board Factory Crashed", e);
			System.exit(0);
		}
		Log.i("Game", "create timer buttons");
		bStart = (Button) findViewById(R.id.starttimer);
		bPause = (Button) findViewById(R.id.pausetimer);
		
		// TODO board.getCharAt(x,y);
		
		//BUTTONS-----------------------------------------------------------------
		// creating START Button
		bStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (bStart.getText().equals("QUIT")){
					Intent intent = new Intent(gameActivity, MainMenu.class);
					game.startActivity(intent);
					game.finish();
				}
				startTimer();
				bPause.setClickable(true);
				setListener();
				fillBoard();
				bStart.setText("QUIT");
			}
		});
		// Button Pause
		bPause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				timer.cancel();
				timerView.setText("PAUSED");
				Intent intent = new Intent(gameActivity, Pause.class);
				startActivity(intent);
			}
		});
		bPause.setClickable(false);
		//--------------------------------------------------------------------------
		
		layout = (LinearLayout) findViewById(R.id.gamespace);
		TextView score = (TextView) game.findViewById(R.id.score);
		score.setText("Score: " + board.getBoardScore());
	}
	
	public void setListener(){
		layout.setOnTouchListener(new CustomOnTouchListener(board,this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	public void startTimer() {
		// countdown atm 2min displayed in sec
		timer = new CountDownTimer(getMillisInFuture(), 1000) {

			// displays a new time every tick
			public void onTick(long millisUntilFinished) {
				TextView text = (TextView) findViewById(R.id.timer);
				text.setText("You have " + (millisUntilFinished / 1000) + " sec remaining!");
				game.setMillisInFuture(millisUntilFinished);
			}

			public void onFinish() {
				TextView text = (TextView) findViewById(R.id.timer);
				text.setText("Time's up!");
				game.finish();
			}
		};

		timer.start();
	}
	
	//adds char to arraylist "word" 
	public void addLetter(int index, char c){
		word.add(index, c);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	
	}
	
	public long getMillisInFuture() {
		return millisInFuture;
	}
	
	
	public void setMillisInFuture (long value) {
		this.millisInFuture = value;
	}
	

	
	@Override
	public void finish() {
		this.intent = new Intent(this, AfterGame.class);
		this.intent.putExtra("seed", board.getSeed());
		this.intent.putExtra("score", board.getBoardScore());
		this.intent.putExtra("time", this.millisInFuture);
		startActivity(intent);
		super.finish();
	}
	
	public void update() {
		if (board.isCompleted()) {
			Log.i("Game", "board completed");
			this.finish();
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (paused == true){
			bStart.setText("");
			startTimer();
//			afterTimer.start();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		paused = true;
		bStart.setClickable(false);
	}	
	
	public void fillBoard(){
	Log.i("Game", "fill buttons");
	for (int i = 0; i < 36; i++) {
		CustomButton btn = ButtonListProvider.getInstance().getButtonAtIndex(i);
		btn.setText("" + board.getCharAt(i % 6, i / 6));
	}
	Log.i("Game", "setup timer");
	}
}
