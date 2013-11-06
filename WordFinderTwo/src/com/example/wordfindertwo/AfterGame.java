package com.example.wordfindertwo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AfterGame extends Activity {

	String boardSeed;
	int boardScore;
	long timerValue;
	int finalScore;
	
	public final static double TIME_SCORING_FACTOR = 0.0001;
	public final static double WORD_SCORING_FACTOR = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_game);
		
		Intent intent = this.getIntent();
		
		boardSeed = intent.getStringExtra("seed");
		boardScore = intent.getIntExtra("score", 0);
		timerValue = intent.getLongExtra("time", 0);
		
		this.calculateFinalScore();
		
		TextView scoreButton = (TextView) findViewById(R.id.scorecustomdisplay);
		scoreButton.setText("" + finalScore);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.after_game, menu);
		return true;
	}
	
	public void returnToMainMenu(View view){
		Intent intent = new Intent(this, MainMenu.class);
		startActivity(intent);
	}
	
	private void calculateFinalScore() {
		this.finalScore = (int) (this.boardScore * WORD_SCORING_FACTOR + this.timerValue * TIME_SCORING_FACTOR);
	}
	
}
