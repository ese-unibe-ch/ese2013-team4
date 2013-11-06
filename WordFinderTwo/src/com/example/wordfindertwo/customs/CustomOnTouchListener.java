package com.example.wordfindertwo.customs;

import java.util.ArrayList;

import com.example.wordfindertwo.ButtonListProvider;
import com.example.wordfindertwo.R;
import com.example.wordfindertwo.core.Point;
import com.example.wordfindertwo.core.SelectionStatus;
import com.example.wordfindertwo.core.board.Board;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class CustomOnTouchListener implements OnTouchListener {

	ArrayList<Point> list, tempList;
	Board board;
	Activity game;
	TextView score;

	public CustomOnTouchListener(Board board, Activity a) {
		this.board = board;
		game = a;
		this.tempList = new ArrayList<Point>();
	}

	@Override
	public boolean onTouch(View view, MotionEvent me) {
		int event = me.getAction();

		if (event == MotionEvent.ACTION_DOWN || event == MotionEvent.ACTION_MOVE || event == MotionEvent.ACTION_UP) {

			CustomButton button = ButtonListProvider.getInstance().getButtonUnder(me.getRawX(), me.getRawY());
			
			if (tempList.size() == 0 || !tempList.get(tempList.size() - 1).equals(button)) {
				tempList.add(button.getPoint());
				Log.d("CustomOnTouchListener", "moved to " + button.getPoint().toString());
			}

		}
		
		if (event == MotionEvent.ACTION_UP) {
			
			Log.i("CustomOnTouchListener", "finishing sequence");
			
			list.clear();
			list.addAll(tempList);

			tempList.clear();
			
			SelectionStatus result = board.submit(list);
			
			//EVALUATE RESULT
			switch (result) {
			case SelectionGood:
				break;
			case SelectionOld:
				break;
			case SelectionBad:
				break;
			case SelectionInvalid:
				break;
			}
			
			//PRINT SCORE
			score = (TextView) game.findViewById(R.id.Score);
			score.setText("" + board.getBoardScore());
		}
		// END
		return true;
	}

	public void motionEventDown(CustomButton v) {

	}
}
