package com.example.wordfindertwo.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {
	
	String letter;
	CustomButton but = this;
	
	public void setText(){
		this.setText(letter);
	}

	public CustomButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

}
