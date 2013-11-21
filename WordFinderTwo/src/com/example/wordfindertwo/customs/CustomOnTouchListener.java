package com.example.wordfindertwo.customs;

import java.util.ArrayList;

import com.example.wordfindertwo.ButtonListProvider;
import com.example.wordfindertwo.Game;
import com.example.wordfindertwo.R;
import com.example.wordfindertwo.core.Point;
import com.example.wordfindertwo.core.SelectionStatus;
import com.example.wordfindertwo.core.board.Board;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class CustomOnTouchListener implements OnTouchListener {

	private ArrayList<CustomButton> buttonList;
	private Board board;
	private Game game;
	private TextView score;

	public CustomOnTouchListener(Board board, Game game) {
		this.board = board;
		this.game = game;
		this.buttonList = new ArrayList<CustomButton>();
	}

	@Override
	public boolean onTouch(View view, MotionEvent me) {
		int event = me.getAction();
		//any button interaction is recorded
		if (event == MotionEvent.ACTION_DOWN
				|| event == MotionEvent.ACTION_MOVE
				|| event == MotionEvent.ACTION_UP) {
			CustomButton button = ButtonListProvider.Instance.getButtonUnder(me.getRawX(), me.getRawY());
			if (button != null
					&& (this.buttonList.size() == 0 || !this.buttonList
							.get(this.buttonList.size() - 1).getPoint()
							.equals(button.getPoint()))) {
				this.buttonList.add(button);
				// TODO: color new button
				button.setBackgroundColor(Color.GRAY);
				Log.d("CustomOnTouchListener", "moved to "
						+ button.getPoint().toString() + " - List Size is now "
						+ this.buttonList.size());
			}
		}
		//sequence is submitted when the screen is no longer touched
		if (event == MotionEvent.ACTION_UP) {
			Log.i("CustomOnTouchListener", "finishing sequence");
			ArrayList<Point> pointList = new ArrayList<Point>();
			for (CustomButton btn : this.buttonList) {
				pointList.add(btn.getPoint());
			}
			this.paintResult(this.board.submit(pointList));
			this.updateScore();
			this.game.update();
			this.buttonList.clear();
		}
		return true;
	}
	
	private void paintResult(SelectionStatus result) {
		switch (result) {
		case SelectionGood:
			for (CustomButton btn : this.buttonList) {
				btn.setBackgroundColor(Color.GREEN);
			}
			//this.game.update();
			break;

		case SelectionOld:
			for (CustomButton btn : this.buttonList) {
				btn.setBackgroundColor(Color.YELLOW);
			}
			break;

		case SelectionBad:
			for (CustomButton btn : this.buttonList) {
				btn.setBackgroundColor(Color.RED);
			}
			break;

		case SelectionInvalid:
			this.resetButtonColors();
			break;
		}
		//trigger 1s delayed button reset
		new DelayedOperation(1000) {

			@Override
			public void operation() {
				resetButtonColors();
			}
		};
	}

	private void updateScore() {
		this.score = (TextView) this.game.findViewById(R.id.score);
		this.score.setText("	" + this.board.getBoardScore());
	}

	private void resetButtonColors() {
		for (CustomButton btn : ButtonListProvider.Instance.getList()) {
			btn.setBackgroundResource(android.R.drawable.btn_default);
		}
	}

}
