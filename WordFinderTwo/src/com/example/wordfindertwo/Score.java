package com.example.wordfindertwo;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wordfindertwo.core.GameResult;
import com.example.wordfindertwo.data.ScoreDataSource;

public class Score extends Activity {
	private ScoreDataSource datasource;
	private ListView lv;
	List<GameResult> values;

	public void onCreate(Bundle icicle)

	{
		super.onCreate(icicle);
		setContentView(R.layout.activity_score);
		lv = (ListView) findViewById(R.id.score_list);

		datasource = new ScoreDataSource(this);
		datasource.open();

		values = datasource.getAllScores();

		lv.setAdapter(new ArrayAdapter<GameResult>(this,
				android.R.layout.simple_list_item_1, values));
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(Score.this);
				adb.setTitle("Loading Board");
				adb.setMessage( values.get(position).toString() );
				adb.show();
				startIntent(values.get(position));
				
				
			}

			private void startIntent(GameResult result) {
				Intent i = new Intent(getApplicationContext(), Game.class);
				i.putExtra("BoardData", result.getBoardData());
				i.putExtra("BoardID",result.getBoardID());
				i.putExtra("BoardName",result.getName());
				startActivity(i);
				
			}
		});
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}