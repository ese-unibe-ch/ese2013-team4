package com.example.wordfindertwo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Pause extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pause);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pause, menu);
		return true;
	}
	
	/**
	 * switches back to the Game activity and signals it to continue the game
	 */
	public void backToGame(View view){
		this.setResult(0);
		finish();
	}

	/**
	 * switches back to the Game activity and signals it to finish the game
	 */
	public void finishGame(View view){
		this.setResult(1);
		finish();
	}
}
