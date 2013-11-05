package com.example.wordfindertwo.core.board;

import java.util.ArrayList;

import com.example.wordfindertwo.core.SelectionStatus;
import com.example.wordfindertwo.core.Point;

/**
 * Interface with all methods needed to select fields of the board and submit them as solution.
 * 
 */
public interface BoardInputInterface {

	/**
	 * Submits a solution given as the sequence of the field selection.
	 * 
	 * @param sequence
	 * @return status of selection
	 * @see SelectionStatus
	 */
	public SelectionStatus submit(ArrayList<Point> sequence);
	
}
