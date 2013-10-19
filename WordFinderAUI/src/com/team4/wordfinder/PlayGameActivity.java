package com.team4.wordfinder;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class PlayGameActivity extends Activity {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		
		//countdown atm 2min displayed in sec
		new CountDownTimer(120000, 1000){
			
			//displays a new time every tick
			public void onTick(long millisUntilFinished){
				TextView text = (TextView) findViewById(R.id.textView2);
				text.setText("You have " + (millisUntilFinished/1000)+ " sec remaining!");
			}
			
			public void onFinish(){
				TextView text = (TextView) findViewById(R.id.textView2);
				text.setText("Time's up!");
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_game, menu);
		return true;
	}
	
}
