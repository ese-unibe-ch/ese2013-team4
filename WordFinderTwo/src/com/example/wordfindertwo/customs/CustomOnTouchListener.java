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

	public CustomOnTouchListener(Board board, Game g) {
		this.board = board;
		game = g;
		this.buttonList = new ArrayList<CustomButton>();
	}

	@Override
	public boolean onTouch(View view, MotionEvent me) {
		int event = me.getAction();

		if (event == MotionEvent.ACTION_DOWN
				|| event == MotionEvent.ACTION_MOVE
				|| event == MotionEvent.ACTION_UP) {
			CustomButton button = ButtonListProvider.getInstance()
					.getButtonUnder(me.getRawX(), me.getRawY());
			if (button != null
					&& (buttonList.size() == 0 || !buttonList
							.get(buttonList.size() - 1).getPoint()
							.equals(button.getPoint()))) {
				buttonList.add(button);
				// TODO: color new button
				button.setBackgroundColor(Color.GRAY);
				Log.d("CustomOnTouchListener", "moved to "
						+ button.getPoint().toString() + " - List Size is now "
						+ this.buttonList.size());
			}
		}

		if (event == MotionEvent.ACTION_UP) {
			Log.i("CustomOnTouchListener", "finishing sequence");
			ArrayList<Point> pointList = new ArrayList<Point>();
			for (CustomButton btn : buttonList) {
				pointList.add(btn.getPoint());
			}

			// TODO: add

			SelectionStatus result = board.submit(pointList);

			// EVALUATE RESULT
			switch (result) {
			case SelectionGood:
				for (CustomButton btn : buttonList) {
					btn.setBackgroundColor(Color.GREEN);
				}
				game.update();
				break;

			case SelectionOld:
				for (CustomButton btn : buttonList) {
					btn.setBackgroundColor(Color.YELLOW);
				}
				break;

			case SelectionBad:
				for (CustomButton btn : buttonList) {
					btn.setBackgroundColor(Color.RED);
				}
				break;

			case SelectionInvalid:
				this.resetButtonColors();
				break;
			}

			// PRINT SCORE
			score = (TextView) game.findViewById(R.id.score);
			score.setText("	" + board.getBoardScore());
			// paint buttons neutral after 1 second delay
			new DelayedOperation(1000) {

				@Override
				public void operation() {
					resetButtonColors();
				}
			};

			// cleanup
			buttonList.clear();
		}

		// END
		return true;
	}

	private void resetButtonColors() {
		for (CustomButton btn : ButtonListProvider.getInstance().getList()) {
			btn.setBackgroundResource(android.R.drawable.btn_default);
		}
	}

}
