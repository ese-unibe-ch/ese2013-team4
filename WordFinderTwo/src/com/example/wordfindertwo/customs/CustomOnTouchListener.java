package com.example.wordfindertwo.customs;

import java.util.ArrayList;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class CustomOnTouchListener implements OnTouchListener{
	
	ArrayList<Character> tempList;

	@Override
	public boolean onTouch(View view, MotionEvent me) {
		LinearLayout v = (LinearLayout) view;
		
		switch(me.getAction()){
		case MotionEvent.ACTION_DOWN:
			v.getChildAt(0);
			break;
		case MotionEvent.ACTION_MOVE:
			v.getChildAt(0);
			break;
		case MotionEvent.ACTION_UP:
			v.getChildAt(0);
			break;
		}
		
		return true;
	}
	
	public void motionEventDown(CustomButton v){
		
	}
}
