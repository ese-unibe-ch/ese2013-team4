package com.example.wordfindertwo;

import com.example.wordfindertwo.Dictionary;
import com.example.wordfindertwo.FriendsTable;
import com.example.wordfindertwo.Game;
import com.example.wordfindertwo.R;
import com.example.wordfindertwo.Score;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainMenu extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
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
		Intent intent = new Intent(this, FriendsTable.class);
		startActivity(intent);	
	}
	
	public void openDictionary(View view){
		Intent intent = new Intent(this, Dictionary.class);
		startActivity(intent);	
	}
	
}
