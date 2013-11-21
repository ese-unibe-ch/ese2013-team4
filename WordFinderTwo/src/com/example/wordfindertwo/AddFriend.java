package com.example.wordfindertwo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddFriend extends Activity{
	
	private Button addFriendByEmailButton;
	private Button searchFriendByEmailButton;
	private EditText searchFriendByMailEditText;
	private EditText foundFriendEmailEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addfriend);
		this.addFriendByEmailButton = (Button) findViewById(R.id.addFriendButton);
		this.searchFriendByEmailButton = (Button) findViewById(R.id.searchFriendbyMailButton);
		this.searchFriendByMailEditText = (EditText) findViewById(R.id.searchFriendByMailEditText);
		this.foundFriendEmailEditText = (EditText) findViewById(R.id.foundFriendEmailEditText);
	}
}
