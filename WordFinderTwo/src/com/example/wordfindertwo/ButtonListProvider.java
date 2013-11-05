package com.example.wordfindertwo;

import java.util.ArrayList;

import com.example.wordfindertwo.customs.CustomButton;

public class ButtonListProvider {
	
	private static ButtonListProvider instance;
	
	public static ButtonListProvider getInstance() {
		return instance;
	}
	
	
	// CLASS MANAGEMENT
	ArrayList<CustomButton> buttonList;

	public ButtonListProvider(Game game){
		buttonList.add();
	}
}
