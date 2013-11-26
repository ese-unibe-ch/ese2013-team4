package com.example.wordfindertwo;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.wordfindertwo.customs.CustomButton;

public enum ButtonListProvider {

	Instance;

	// CLASS MANAGEMENT
	public final static String BUTTON_ID_PREFIX = "button";
	public final static String MAIN_PACKAGE_NAME = "com.example.wordfindertwo";

	ArrayList<CustomButton> buttonList;

	private ButtonListProvider() {
		return;
	}

	/**
	 * Configures the ButtonListProvider for the given game instance.
	 * 
	 * @param game
	 */
	public void configureForGame(Game game) {
		Log.i("ButtonListProvider", "building button list");
		buttonList = new ArrayList<CustomButton>();
		for (int i = 0; i < 36; i++) {
			CustomButton btn = (CustomButton) findView(game.getBaseContext(),
					game, BUTTON_ID_PREFIX + this.convertIndexToCoords(i));
			btn.setCoords(i % 6, i / 6);
			buttonList.add(btn);
			Log.i("BLP", this.convertIndexToCoords(i));
		}
	}

	private String convertIndexToCoords(int i) {
		int x = i % 6;
		int y = i / 6;
		return x + "" + y;
	}

	/**
	 * @return a sorted list of all buttons. The Buttons are stored row by row,
	 *         top down
	 */
	public ArrayList<CustomButton> getList() {
		return buttonList;
	}

	/**
	 * Returns the button with a specific index.<br/>
	 * The index for <tt>buttonxy</tt> is <tt>x + 6y</tt>
	 */
	public CustomButton getButtonAtIndex(int index) {
		return this.buttonList.get(index);
	}

	/**
	 * Finds the button that is positioned under the given coordinates
	 * <tt>x</tt> and <tt>y</tt>.
	 * 
	 * @return the button at the given coordinates, or <tt>null</tt> when
	 *         there's no button.
	 */
	public CustomButton getButtonUnder(float x, float y) {
		for (CustomButton button : this.buttonList) {
			if (button.isTouched(x, y))
				return button;
		}
		return null;
	}

	private View findView(Context context, Game game, String res) {
		int resID = context.getResources().getIdentifier(res, "id",
				MAIN_PACKAGE_NAME);
		return game.findViewById(resID);
	}
}
