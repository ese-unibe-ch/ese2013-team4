package com.team4.wordfinder;

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
	
	public void openPlayActivity(View view){
		Intent intent = new Intent(this, PlayGame.class);
		startActivity(intent);
	}
	
	public void openScoresActivity(View view){
		Intent intent = new Intent(this, PlayGame.class);
		startActivity(intent);
	}
	
	public void openFriendsActivity(View view){
		Intent intent = new Intent(this, PlayGame.class);
		startActivity(intent);
	}
	
	public void openDictionariesActivity(View view){
		Intent intent = new Intent(this, PlayGame.class);
		startActivity(intent);
	}
}
