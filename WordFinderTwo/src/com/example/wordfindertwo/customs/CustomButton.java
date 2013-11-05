package com.example.wordfindertwo.customs;

import com.example.wordfindertwo.R;
import com.example.wordfindertwo.core.Point;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {

	int xCoord;
	int yCoord;
	Point p;
	

	public CustomButton(Context context) {
		super(context);
		setClickable(false);
		setText("A");
		p = new Point(xCoord,yCoord);
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setClickable(false);
		init(context, attrs);
		p = new Point(xCoord,yCoord);
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setClickable(false);
		init(context, attrs);
		p = new Point(xCoord,yCoord);
	}

	private void init(Context context, AttributeSet attrs){
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
		final String letter = a.getString(R.styleable.CustomButton_letter);
		this.setText(letter);
		xCoord = a.getInt(R.styleable.CustomButton_xCoord, 0);
		yCoord = a.getInt(R.styleable.CustomButton_yCoord, 0);
		a.recycle();
	}
	
	public int[] getCoord(){
		return new int[]{xCoord, yCoord};
	}
	
	public Point getPoint(){
		return p;
	}
	
	public boolean isTouched(float x, float y) {
		int location[] = new int[2];
	    this.getLocationOnScreen(location);
	    int viewX = location[0];
	    int viewY = location[1];

	    //point is inside view bounds
	    return x > viewX && x < (viewX + this.getWidth()) && y > viewY && y < (viewY + this.getHeight());
	}
}
