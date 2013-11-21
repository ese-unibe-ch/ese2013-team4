package com.example.wordfindertwo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AfterGame extends Activity {

	private final static double TIME_SCORING_FACTOR = 0.0001;
	private final static double WORD_SCORING_FACTOR = 1;
	
	private String boardSeed; //will be needed for board storage
	private int boardScore;
	private int finalScore;
	private long timerValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_game);
		
		this.boardSeed = this.getIntent().getStringExtra("seed");
		this.boardScore = this.getIntent().getIntExtra("score", 0);
		this.timerValue = this.getIntent().getLongExtra("time", 0);
		
		this.calculateFinalScore();
		
		((TextView) findViewById(R.id.scorecustomdisplay)).setText("" + finalScore);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.after_game, menu);
		return true;
	}
	
	/**
	 * switches back to the main menu
	 */
	public void returnToMainMenu(View view){
		Intent intent = new Intent(this, MainMenu.class);
		startActivity(intent);
	}
	
	private void calculateFinalScore() {
		this.finalScore = (int) (this.boardScore * WORD_SCORING_FACTOR + this.timerValue * TIME_SCORING_FACTOR);
	}
	
}
