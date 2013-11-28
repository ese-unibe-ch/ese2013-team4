package com.example.wordfindertwo.test;

import com.example.wordfindertwo.core.GameResult;

import android.test.AndroidTestCase;

public class GameResultTest extends AndroidTestCase {

	public void testGettersAndSetters() {
		GameResult gr = new GameResult(1, "data");
		assertEquals(1, gr.getBoardID());
		assertEquals("data", gr.getBoardData());
		assertEquals(0, gr.getScore());
		gr.setBoardID(2);
		assertEquals(2, gr.getBoardID());
	}

	public void testScoreHandling() {
		GameResult gr = new GameResult(1, "data");
		assertEquals(0, gr.getScore());
		// add word with value 0
		gr.addWord(0);
		assertEquals((int) (GameResult.WORD_SCORE_MULTIPLIER), gr.getScore());
		// add word with value 1
		int score = gr.getScore();
		gr.addWord(1);
		score += GameResult.WORD_SCORE_MULTIPLIER
				+ (int) (GameResult.LETTER_SCORE_MULTIPLIER * 1);
		assertEquals(score, gr.getScore());
		// add time bonus
		gr.addTimeBonus(10000);
		score += (int) (GameResult.TIME_SCORE_MULTIPLIER * 10000);
		assertEquals(score, gr.getScore());
	}

}
