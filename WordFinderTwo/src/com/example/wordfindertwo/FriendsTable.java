package com.example.wordfindertwo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;


public class FriendsTable extends Activity{


	RadioButton deleteOnRadioButton;
	Button addFriendButton;
	
	private TableLayout friendsTable_scrollView_TableLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendtable);
		
		friendsTable_scrollView_TableLayout =(TableLayout) findViewById(R.id.friendsTable_scrollView_TableLayout);
		deleteOnRadioButton = (RadioButton) findViewById(R.id.deleteFriend);
		addFriendButton = (Button) findViewById(R.id.addFriendButton);
		
		deleteOnRadioButton.setOnClickListener((android.view.View.OnClickListener) deleteOnRadioButtonListener);
				
	}
	
	private OnClickListener deleteOnRadioButtonListener =new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			View button = findViewById(R.id.friends_tableRow_deleteButton);
			button.setVisibility(View.VISIBLE);
			
		}
	};
	
	private void insertFriendInScrollView(String friendNameMail, int Arrayindex){
		
		
		LayoutInflater friendsInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View newFriendRow = friendsInflater.inflate(R.layout.activity_friend_tablerow, null);
		TextView friends_tablRow_TextsView = (TextView) newFriendRow.findViewById(R.id.friends_tablRow_TextsView);
		friends_tablRow_TextsView.setText(friendNameMail);
		
		Button friends_tableRow_deleteButton = (Button) newFriendRow.findViewById(R.id.friends_tableRow_deleteButton);
		friends_tableRow_deleteButton.setOnClickListener((android.view.View.OnClickListener) friends_tableRow_deleteButtonListener);

		friendsTable_scrollView_TableLayout.addView(friends_tablRow_TextsView, Arrayindex);
	}
	
	
	
	private OnClickListener friends_tableRow_deleteButtonListener= new OnClickListener() {
		
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			friendsTable_scrollView_TableLayout.removeViewAt(which);
			
			
		}
	};
	
	
	
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
		
}
