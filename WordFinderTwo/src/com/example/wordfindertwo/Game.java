package com.example.wordfindertwo;

import com.example.wordfindertwo.R;
import com.example.wordfindertwo.customs.CustomOnTouchListener;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import java.util.*;

public class Game extends Activity {
	
	// -------------timer Variables------------------
	private final long START_TIME = 120000;
	private long millisInFuture;
	private TextView timerText;
	private CountDownTimer timer;
	private Activity a = this;
	// -----------------------------------------------

	LinearLayout gameLayout;
	static ArrayList<Character> word;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		Button bStart = (Button) findViewById(R.id.starttimer);
		Button bPause = (Button) findViewById(R.id.pausetimer);

		timer = new CountDownTimer(START_TIME, 1000) {
			// displays a new time every tick
			public void onTick(long millisUntilFinished) {
				timerText = (TextView) findViewById(R.id.timer);
				timerText.setText("" + (millisUntilFinished / 1000));
			}

			public void onFinish() {
				TextView text = (TextView) findViewById(R.id.timer);
				text.setText("Time's up!");
			}
		};

		// START Button
		bStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				timer.start();
			}
		});


		// Button Pause
		bPause.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				millisInFuture = Long.parseLong(timerText.getText().toString());
				timer.cancel();
				timerText.setText("PAUSED");
				Intent intent = new Intent(a, PauseScreen.class);
				startActivity(intent);
			}
		});

		
		gameLayout = (LinearLayout) findViewById(R.id.gamespace);
		gameLayout.setOnTouchListener(new CustomOnTouchListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	public void startTimer() {
		// countdown atm 2min displayed in sec
		CountDownTimer timer = new CountDownTimer(120000, 1000) {
			// displays a new time every tick
			public void onTick(long millisUntilFinished) {
				TextView text = (TextView) findViewById(R.id.timer);
				text.setText("You have " + (millisUntilFinished / 1000)
						+ " sec remaining!");
			}

			public void onFinish() {
				TextView text = (TextView) findViewById(R.id.timer);
				text.setText("Time's up!");
			}
		};

		timer.start();
	}
	
	//adds char to arraylist "word" 
	public void addLetter(int index, char c){
		word.add(index, c);
	}
}
