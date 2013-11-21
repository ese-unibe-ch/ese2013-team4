package com.example.wordfindertwo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FriendsTable extends Activity{

	private TextView friendsTable_TitleTextView;
	private RadioButton deleteOnRadioButton;
	private Button addFriendButton;
	private TableRow friendsTable_FirstTableRow;
	private TableRow friendsTabel_TableRow_holdsScrollViewAndTableRow;
	private TableRow addAndDeleteFriendTableRow;
	private ScrollView friendsTable_ScrollView_inTableRow;
	private TableLayout friendsTable_outerTableLayout;
	private TableLayout friendsTable_scrollView_TableLayout;
	
	private OnClickListener deleteOnRadioButtonListener = new OnClickListener(){
		
		public void onClick(View v) {
			View button = findViewById(R.id.friends_tableRow_deleteButton);
			button.setVisibility(View.VISIBLE);
		}
	};
	private OnClickListener addFriendButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(),AddFriend.class);
            startActivity(i);
		}
	
	};
	private OnClickListener friends_tableRow_deleteButtonListener= new OnClickListener() {

		@Override
		public void onClick(View v) {
			friendsTable_scrollView_TableLayout.removeViewAt(R.id.friendsTable_FirstTableRow);;			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendtable);
		this.friendsTable_scrollView_TableLayout =(TableLayout) findViewById(R.id.friendsTable_scrollView_TableLayout);
		this.deleteOnRadioButton = (RadioButton) findViewById(R.id.deleteFriend);
		this.deleteOnRadioButton.setOnClickListener(deleteOnRadioButtonListener);
		this.addFriendButton = (Button) findViewById(R.id.addFriendButton);
		this.addFriendButton.setOnClickListener(addFriendButtonListener);
	}

	private void insertFriendInScrollView(String friendNameMail, int Arrayindex){
		LayoutInflater friendsInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View newFriendRow = friendsInflater.inflate(R.layout.activity_friend_tablerow, null);
		TextView friends_tablRow_TextsView = (TextView) newFriendRow.findViewById(R.id.friends_tablRow_TextsView);
		friends_tablRow_TextsView.setText(friendNameMail);
		Button friends_tableRow_deleteButton = (Button) newFriendRow.findViewById(R.id.friends_tableRow_deleteButton);
		friends_tableRow_deleteButton.setOnClickListener(this.friends_tableRow_deleteButtonListener);
		this.friendsTable_scrollView_TableLayout.addView(friends_tablRow_TextsView, Arrayindex);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friendstable, menu);
		return true;
	}
	
	/**
	 * switches to the AddFriend activity
	 */
	public void addFriend(View view){
		Intent intent = new Intent(this, AddFriend.class);
		startActivity(intent);
	}
		
}
