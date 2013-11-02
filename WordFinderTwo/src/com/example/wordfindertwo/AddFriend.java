package com.example.wordfindertwo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class AddFriend extends Activity{
	
	Button addFriendByEmailButton;
	Button searchFriendByEmailButton;
	
	EditText searchFriendByMailEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addfriend);
		
	
		addFriendByEmailButton = (Button) findViewById(R.id.addFriendButton);
		searchFriendByEmailButton = (Button) findViewById(R.id.searchFriendbyMailButton);
		searchFriendByMailEditText = (EditText) findViewById(R.id.searchFriendByMailEditText);
	}
	
	
}
