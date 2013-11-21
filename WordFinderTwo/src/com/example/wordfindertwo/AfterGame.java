package com.example.wordfindertwo;

import com.example.wordfindertwo.core.GameResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AfterGame extends Activity {
	
	private GameResult result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_game);
		this.result = GameResult.unserialize(this.getIntent().getStringExtra(
				"GameResult"));

		((TextView) findViewById(R.id.scorecustomdisplay)).setText("" + this.result.getScore());
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
	public void returnToMainMenu(View view) {
		startActivity(new Intent(this, MainMenu.class));
		finish();
	}
	
	@Override
	public void onBackPressed() {
		returnToMainMenu(null);
	}

}
