package com.example.wordfindertwo;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.wordfindertwo.data.DatabaseHelper;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

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
	
	public void openFriend(View view){
		DatabaseHelper db = new DatabaseHelper(this);
		Intent intent;
		if (db.getPrimaryUserName() == null)
			intent = new Intent(this, LoginActivity.class);
		else
			intent = new Intent(this, FriendsTable.class);
		db.close();
		startActivity(intent);

	}
	
	public void openDictionary(View view){
		Intent intent = new Intent(this, Dictionary.class);
		startActivity(intent);	
	}
	
	@Override
	public void onBackPressed() {
		//NO-OP
	}
	
}
