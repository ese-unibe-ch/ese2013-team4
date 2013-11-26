package com.example.wordfindertwo.customs;

import com.example.wordfindertwo.ButtonListProvider;
import com.example.wordfindertwo.R;
import com.example.wordfindertwo.Game;
import com.example.wordfindertwo.core.Point;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomButton extends Button {

	int xCoord;
	int yCoord;
	Point p;

	public final static double BUTTON_PADDING_FACTOR = 0.2;

	public CustomButton(Context context) {
		super(context);
		setClickable(false);
		setText("A");
		p = new Point(xCoord, yCoord);
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setClickable(false);
		init(context, attrs);
		p = new Point(xCoord, yCoord);
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setClickable(false);
		init(context, attrs);
		p = new Point(xCoord, yCoord);
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CustomButton);
		final String letter = a.getString(R.styleable.CustomButton_letter);
		this.setText(letter);
		xCoord = a.getInt(R.styleable.CustomButton_xCoord, 0);
		yCoord = a.getInt(R.styleable.CustomButton_yCoord, 0);
		a.recycle();
	}

	public int[] getCoord() {
		return new int[] { xCoord, yCoord };
	}

	public Point getPoint() {
		return p;
	}

	public boolean isTouched(float x, float y) {
		int location[] = new int[2];
		this.getLocationOnScreen(location);

		int viewX = location[0];
		int viewY = location[1];

		return x >= viewX + BUTTON_PADDING_FACTOR * this.getWidth()
				&& x <= viewX + (1 - BUTTON_PADDING_FACTOR) * this.getWidth()
				&& y >= viewY + BUTTON_PADDING_FACTOR * this.getHeight()
				&& y <= viewY + (1 - BUTTON_PADDING_FACTOR) * this.getHeight();
	}

	public void setValueText(String s) {
		TextView letter = (TextView) findView(Game.game.getBaseContext(),
				Game.game, "letter" + xCoord + "" + yCoord);
		letter.setText(s);
	}

	private View findView(Context context, Game game, String res) {
		int resID = context.getResources().getIdentifier(res, "id",
				ButtonListProvider.MAIN_PACKAGE_NAME);
		return game.findViewById(resID);
	}
}
