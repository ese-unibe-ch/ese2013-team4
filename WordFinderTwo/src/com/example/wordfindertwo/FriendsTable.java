package com.example.wordfindertwo;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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

	TextView friendsTable_TitleTextView;
	RadioButton deleteOnRadioButton;
	Button addFriendButton;
	TableRow friendsTable_FirstTableRow;
	TableRow friendsTabel_TableRow_holdsScrollViewAndTableRow;
	TableRow addAndDeleteFriendTableRow;
	ScrollView friendsTable_ScrollView_inTableRow;
	TableLayout friendsTable_outerTableLayout;
	private TableLayout friendsTable_scrollView_TableLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendtable);
		
		friendsTable_scrollView_TableLayout =(TableLayout) findViewById(R.id.friendsTable_scrollView_TableLayout);
		
		deleteOnRadioButton = (RadioButton) findViewById(R.id.deleteFriend);
		deleteOnRadioButton.setOnClickListener(deleteOnRadioButtonListener);
		
		addFriendButton = (Button) findViewById(R.id.addFriendButton);
		addFriendButton.setOnClickListener(addFriendButtonListener);

	}
	
	
	public OnClickListener deleteOnRadioButtonListener = new OnClickListener(){
		
		public void onClick(View v) {
			View button = findViewById(R.id.friends_tableRow_deleteButton);
			button.setVisibility(View.VISIBLE);
			
		}
	};

	public OnClickListener addFriendButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(),AddFriend.class);
            startActivity(i);
	
		}
	
	};

	private void insertFriendInScrollView(String friendNameMail, int Arrayindex){
		
		
		LayoutInflater friendsInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View newFriendRow = friendsInflater.inflate(R.layout.activity_friend_tablerow, null);
		
		TextView friends_tablRow_TextsView = (TextView) newFriendRow.findViewById(R.id.friends_tablRow_TextsView);
		friends_tablRow_TextsView.setText(friendNameMail);
		
		Button friends_tableRow_deleteButton = (Button) newFriendRow.findViewById(R.id.friends_tableRow_deleteButton);
//		friends_tableRow_deleteButton.setOnClickListener(friends_tableRow_deleteButtonListener);

		friendsTable_scrollView_TableLayout.addView(friends_tablRow_TextsView, Arrayindex);
	}
	
	
	
	public OnClickListener friends_tableRow_deleteButtonListener= new OnClickListener() {

		@Override
		public void onClick(View v) {
			friendsTable_scrollView_TableLayout.removeViewAt(R.id.friendsTable_FirstTableRow);;			
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
