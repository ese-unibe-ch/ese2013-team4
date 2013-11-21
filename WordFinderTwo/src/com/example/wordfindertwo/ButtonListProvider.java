package com.example.wordfindertwo;

import java.util.ArrayList;

import android.util.Log;

import com.example.wordfindertwo.customs.CustomButton;

public enum ButtonListProvider {
	
	Instance;
	
	// CLASS MANAGEMENT
	ArrayList<CustomButton> buttonList;

	private ButtonListProvider() {
		return;
	}
	
	/**
	 * Configures the ButtonListProvider for the given game instance.
	 * @param game
	 */
	public void configureForGame(Game game) {
		Log.i("ButtonListProvider", "building button list");
		buttonList = new ArrayList<CustomButton>();
		buttonList.add((CustomButton)game.findViewById(R.id.button00));
		buttonList.add((CustomButton)game.findViewById(R.id.button10));
		buttonList.add((CustomButton)game.findViewById(R.id.button20));
		buttonList.add((CustomButton)game.findViewById(R.id.button30));
		buttonList.add((CustomButton)game.findViewById(R.id.button40));
		buttonList.add((CustomButton)game.findViewById(R.id.button50));
		buttonList.add((CustomButton)game.findViewById(R.id.button01));
		buttonList.add((CustomButton)game.findViewById(R.id.button11));
		buttonList.add((CustomButton)game.findViewById(R.id.button21));
		buttonList.add((CustomButton)game.findViewById(R.id.button31));
		buttonList.add((CustomButton)game.findViewById(R.id.button41));
		buttonList.add((CustomButton)game.findViewById(R.id.button51));
		buttonList.add((CustomButton)game.findViewById(R.id.button02));
		buttonList.add((CustomButton)game.findViewById(R.id.button12));
		buttonList.add((CustomButton)game.findViewById(R.id.button22));
		buttonList.add((CustomButton)game.findViewById(R.id.button32));
		buttonList.add((CustomButton)game.findViewById(R.id.button42));
		buttonList.add((CustomButton)game.findViewById(R.id.button52));
		buttonList.add((CustomButton)game.findViewById(R.id.button03));
		buttonList.add((CustomButton)game.findViewById(R.id.button13));
		buttonList.add((CustomButton)game.findViewById(R.id.button23));
		buttonList.add((CustomButton)game.findViewById(R.id.button33));
		buttonList.add((CustomButton)game.findViewById(R.id.button43));
		buttonList.add((CustomButton)game.findViewById(R.id.button53));
		buttonList.add((CustomButton)game.findViewById(R.id.button04));
		buttonList.add((CustomButton)game.findViewById(R.id.button14));
		buttonList.add((CustomButton)game.findViewById(R.id.button24));
		buttonList.add((CustomButton)game.findViewById(R.id.button34));
		buttonList.add((CustomButton)game.findViewById(R.id.button44));
		buttonList.add((CustomButton)game.findViewById(R.id.button54));
		buttonList.add((CustomButton)game.findViewById(R.id.button05));
		buttonList.add((CustomButton)game.findViewById(R.id.button15));
		buttonList.add((CustomButton)game.findViewById(R.id.button25));
		buttonList.add((CustomButton)game.findViewById(R.id.button35));
		buttonList.add((CustomButton)game.findViewById(R.id.button45));
		buttonList.add((CustomButton)game.findViewById(R.id.button55));
	}
	
	/**
	 * @return a sorted list of all buttons. The Buttons are stored row by row, top down
	 */
	public ArrayList<CustomButton> getList(){
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
	 * Finds the button that is positioned under the given coordinates <tt>x</tt> and <tt>y</tt>.
	 * @return the button at the given coordinates, or <tt>null</tt> when there's no button.
	 */
	public CustomButton getButtonUnder(float x, float y) {
		for (CustomButton button : this.buttonList) {
			if (button.isTouched(x, y))
				return button;
		}
		return null;
	}
	
}
