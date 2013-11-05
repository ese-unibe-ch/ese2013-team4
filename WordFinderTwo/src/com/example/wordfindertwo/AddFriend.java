package com.example.wordfindertwo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddFriend extends Activity{
	
	Button addFriendByEmailButton;
	Button searchFriendByEmailButton;
	
	EditText searchFriendByMailEditText;
	EditText foundFriendEmailEditText;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addfriend);
		
	
		addFriendByEmailButton = (Button) findViewById(R.id.addFriendButton);
		searchFriendByEmailButton = (Button) findViewById(R.id.searchFriendbyMailButton);
		
		searchFriendByMailEditText = (EditText) findViewById(R.id.searchFriendByMailEditText);
		foundFriendEmailEditText = (EditText) findViewById(R.id.foundFriendEmailEditText);

	}
	
	
}
