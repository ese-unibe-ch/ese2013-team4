package com.example.wordfindertwo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Tutorial extends Activity {
	private String tutorialText;
	private TextView tutorial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);

		tutorialText = "Welcome to our Word Finder - Tutorial\n"
				+ "\n"
				+ "1. How to generate and play a board\n"
				+ "2. How to retry a board and improve your score\n"
				+ "\n"
				+ "\n"
				+ "1.How to generate and play a board\n"
				+ "\n"
				+ "First, go back to the screen with buttons: \"generate board\", \"score\" and \"tutorial\"."
				+ " After that, press the \"generate board\" - button. At the time the board is generated,"
				+ " you may now press the \"Start\" - button to start the game. You have now a time limit of two minutes."
				+ " After the time run out, or finding all the words in the board the game will automatically finish."
				+ " While playing you may also press the \"Pause\" - button to pause the game.\n"
				+ "\n"
				+ "Rules:\n"
				+ "You can find words by touching the letter, the word begins with and then just hovering over the letters you"
				+ " want to connect with the first one. As soon you lift up your finger from the screen, the board will stop to"
				+ " record the letters. You may connect the letters horizontally and vertically as well as diagonally. Even though"
				+ " you may not use the same letter-button twice in one word. The selection you made will be colored red if your selection"
				+ " isn't a valid word, yellow if you already found this word and green if it is a valid word."
				+ " Every valid found word will be added to the scroll window in the bottom right corner. You will always be able to see"
				+ " the three last words found and the other word you will see if you scroll down in that small section. Also will the number"
				+ " of words found be displayed in the bottom left corner right below the displayed current score.\n"
				+ "\n"
				+ "2.How to retry a board and improve your score\n"
				+ "\n"
				+ "In this application you have the possibility to retry an already played board. First step, go again to the main menu. Then"
				+ " you press the \"Score\" - button. After that you may select any board you want to retry. Just click on the board you want"
				+ " to retry. If you improve your score on the retried board, the score will automatically be updated for you.";

		tutorial = (TextView) findViewById(R.id.tutorialText);
		tutorial.setText(tutorialText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tutorial, menu);
		return true;
	}

}
