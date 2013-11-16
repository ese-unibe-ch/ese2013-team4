package com.example.wordfindertwo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableRow;

public class Scores extends Activity{
	
	TableRow scores_OwnTopFive_ScrollView_TableRow;
	ScrollView scores_OwnTopFive_ScrollView;
	TableRow scores_OwnTopFive_ScrollView_InnerTableRow;
	TableRow scores_OwnLastTwenty_ScrollView_TableRow;
	ScrollView scores_OwnLastTwenty_ScrollView;
	TableRow scores_OwnLastTwenty_ScrollView_InnerTableRow;
	Button scores_DeleteButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		
		scores_OwnTopFive_ScrollView_TableRow= (TableRow) findViewById(R.id.scores_OwnTopFive_ScrollView_TableRow);
		scores_OwnTopFive_ScrollView=(ScrollView) findViewById(R.id.scores_OwnTopFive_ScrollView);
		scores_OwnTopFive_ScrollView_InnerTableRow= (TableRow) findViewById(R.id.scores_OwnTopFive_ScrollView_InnerTableRow);
		scores_OwnLastTwenty_ScrollView_TableRow= (TableRow) findViewById(R.id.scores_OwnLastTwenty_ScrollViwe_TableRow);
		scores_OwnLastTwenty_ScrollView=(ScrollView) findViewById(R.id.scores_OwnLastTwenty_ScrollView);
		scores_OwnLastTwenty_ScrollView_InnerTableRow= (TableRow) findViewById(R.id.scores_OwnLastTwenty_ScrollView_InnerTableRow);
		scores_DeleteButton = (Button) findViewById(R.id.scores_DeleteButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scores, menu);
		return true;
	}


}
