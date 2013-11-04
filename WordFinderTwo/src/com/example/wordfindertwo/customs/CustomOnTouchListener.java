package com.example.wordfindertwo.customs;

import java.util.ArrayList;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CustomOnTouchListener implements OnTouchListener{
	
	ArrayList<Character> tempList;

	@Override
	public boolean onTouch(View view, MotionEvent me) {
		CustomButton v = (CustomButton) view;
		
		switch(me.getAction()){
		case MotionEvent.ACTION_DOWN:
			v.getText();
			break;
		case MotionEvent.ACTION_MOVE:
			v.getText();
			break;
		case MotionEvent.ACTION_UP:
			v.getText();
			break;
		}
		
		return true;
	}
	
	public void motionEventDown(CustomButton v){
		
	}
}
