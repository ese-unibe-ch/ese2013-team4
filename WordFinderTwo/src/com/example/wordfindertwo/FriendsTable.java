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
import android.widget.ListView;


public class FriendsTable extends ListActivity {

	private ListView friendsListView;  
	private ArrayAdapter<String> listAdapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendtable);
		
		
		
		
	    
	    String[] friendsArray = new String[0];
	    ArrayList<String> friendsList = new ArrayList<String>();  
	    friendsList.addAll( Arrays.asList(friendsArray) );
	    
	    // Create ArrayAdapter using the friends list.  
	    listAdapter = new ArrayAdapter<String>(this, R.layout.friend_list_row_layout, friendsList);  
	    
	    listAdapter.add("DomenicoTest");
	    
	    // Find the ListView resource.   
	    friendsListView = (ListView) findViewById( R.id.friendsListView ); 
	    // Set the ArrayAdapter as the ListView's adapter.  
	    friendsListView.setAdapter( listAdapter );  


	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friendstable, menu);
		return true;
	}

}
