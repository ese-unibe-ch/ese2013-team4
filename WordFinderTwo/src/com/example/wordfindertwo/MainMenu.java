package com.example.wordfindertwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseAnalytics;

public class MainMenu extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Parse.initialize(this, "8wi7OLHLbQl6Ul9GMKoKqGlA6g4QZXfT73houPAy", "gWIgOBfcjpiJqrDSBfedseD63VQmSETrQVj6nu4v"); 
		ParseAnalytics.trackAppOpened(getIntent());
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		//preload system dictionaries
		StandardDictionary.values();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void openPlayGame(View view){
		Intent intent = new Intent(this, Game.class);
		startActivity(intent);
	}
	
	public void openScores(View view){
		Intent intent = new Intent(this, Score.class);
		startActivity(intent);
	}
	
	public void openTutorial(View view){
		Intent intent = new Intent(this, Tutorial.class);
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		//NO-OP
	}
	
}
