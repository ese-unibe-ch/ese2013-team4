package com.example.wordfindertwo;

import com.example.wordfindertwo.R;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Game extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
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
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
