package com.example.wordfindertwo.customs;

import java.util.ArrayList;

import com.example.wordfindertwo.R;
import com.example.wordfindertwo.core.Point;
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

		switch (me.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			if (tempList.get(tempList.size()-1) != child.getPoint()) {
				tempList.add(child.getPoint());
			}
			System.out.println("down");
			break;
			
		case MotionEvent.ACTION_MOVE:
			child = (CustomButton) v.getChildAt(0);
			if (tempList.get(tempList.lastIndexOf(Point.class)) != child
					.getPoint()) {
				tempList.add(child.getPoint());
			}
			System.out.println("move");
			break;
		// Finger lifts from screen	
		case MotionEvent.ACTION_UP:
			child = (CustomButton) v.getChildAt(0);
			if (tempList.get(tempList.lastIndexOf(Point.class)) != child
					.getPoint()) {
				tempList.add(child.getPoint());
			}
			System.out.println("up");
			list = tempList;
			switch(board.submit(list)){
			case SelectionGood:
				break;
			case SelectionOld:
				break;
			case SelectionBad:
				break;
			case SelectionInvalid:
				break;
			}
			tempList.clear();
			
			score = (TextView) game.findViewById(R.id.Score); 
			score.setText(""+board.getBoardScore());
			break;
		}

		return true;
	}

	public void motionEventDown(CustomButton v) {

	}
}
