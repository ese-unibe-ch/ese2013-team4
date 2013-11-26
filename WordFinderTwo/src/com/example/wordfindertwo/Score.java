package com.example.wordfindertwo;

import java.util.List;
import java.util.Random;

import org.w3c.dom.Comment;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.wordfindertwo.core.GameResult;
import com.example.wordfindertwo.data.ScoreDataSource;

public class Score extends ListActivity {
  private ScoreDataSource datasource;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_score);

    datasource = new ScoreDataSource(this);
    datasource.open();

    List<GameResult> values = datasource.getAllScores();

    // use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<GameResult> adapter = new ArrayAdapter<GameResult>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
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