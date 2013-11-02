package com.example.wordfindertwo;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;


public class FriendsTable extends ListActivity {

	private ListView friendsListView;  
	private ArrayAdapter<String> listAdapter;

	RadioButton deleteOn;
	Button addFriend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendtable);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friendstable, menu);
		return true;
		
		
	}

	public void addFriend(View view){
		Intent intent = new Intent(this, AddFriend.class);
		startActivity(intent);
	}

	public ArrayList<String> getFriendList(){
		return null;
		
	}
}
