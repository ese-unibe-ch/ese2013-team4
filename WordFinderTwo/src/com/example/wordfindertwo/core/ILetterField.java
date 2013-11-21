package com.example.wordfindertwo.core;

public interface ILetterField {

	public Letter getLetter();
	
	public void select();
	
	public void unselect();
	
	public boolean isSelected();
	
	/**
	 * @return true, if other one of the 8 adjacent Fields but not equal
	 */
	public boolean isAdjacent(ILetterField other);
	
	public int getX();
	
	public int getY();
}
