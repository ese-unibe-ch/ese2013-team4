package com.example.wordfindertwo.customs;

import java.util.ArrayList;

import com.example.wordfindertwo.ButtonListProvider;
import com.example.wordfindertwo.R;
import com.example.wordfindertwo.core.Point;
import com.example.wordfindertwo.core.SelectionStatus;
import com.example.wordfindertwo.core.board.Board;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomOnTouchListener implements OnTouchListener {

	ArrayList<Point> list, tempList;
	Board board;
	Activity game;
	TextView score;

	public CustomOnTouchListener(Board board, Activity a) {
		this.board = board;
		game = a;
	}

	@Override
	public boolean onTouch(View view, MotionEvent me) {
		LinearLayout v = (LinearLayout) view;
		CustomButton child;

		int event = me.getAction();

		if (event == MotionEvent.ACTION_DOWN || event == MotionEvent.ACTION_MOVE || event == MotionEvent.ACTION_UP) {

			CustomButton button = ButtonListProvider.getInstance().getButtonUnder(me.getRawX(), me.getRawY());
			
			if (!tempList.get(tempList.size() - 1).equals(button)) {
				tempList.add(button.getPoint());
			}
			
			System.out.println("down / move / up");

		}
		
		if (event == MotionEvent.ACTION_UP) {
		
			System.out.println("up");
			
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
			

			score = (TextView) game.findViewById(R.id.Score);
			score.setText("" + board.getBoardScore());
		}
		// END
		return true;
	}

	public void motionEventDown(CustomButton v) {

	}
}
