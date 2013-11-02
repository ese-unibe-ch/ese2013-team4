package com.example.wordfindertwo;

import com.example.wordfindertwo.R;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Button;

public class Game extends Activity implements OnTouchListener {
	private final long START_TIME = 120000;
	private long millisInFuture;
	private TextView text;
	private CountDownTimer timer;
	private Activity a = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		Button startB = (Button) findViewById(R.id.starttimer);
		Button pauseB = (Button) findViewById(R.id.pausetimer);
		//START Button
		//------------------------------------------------------------------
		startB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				timer = new CountDownTimer(START_TIME, 1000){
					// displays a new time every tick
					public void onTick(long millisUntilFinished) {
						text = (TextView) findViewById(R.id.timer);
						text.setText(""+(millisUntilFinished / 1000));
					}

					public void onFinish() {
						TextView text = (TextView) findViewById(R.id.timer);
						text.setText("Time's up!");
					}
				};
				
				timer.start();
			}
		});
		//--------------------------------------------------------------------
		
		pauseB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				millisInFuture = Long.parseLong(text.getText().toString());
				timer.cancel();
				text.setText("PAUSED");
				Intent intent = new Intent(a, PauseScreen.class);
				startActivity(intent);
			}
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent me) {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		switch (me.getAction()) {
		case MotionEvent.ACTION_DOWN:

			break;
		case MotionEvent.ACTION_UP:

			break;
		case MotionEvent.ACTION_MOVE:

			break;
		}
		return true;
	}

	public void startTimer() {
		// countdown atm 2min displayed in sec
		CountDownTimer timer = new CountDownTimer(120000, 1000){
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

}
