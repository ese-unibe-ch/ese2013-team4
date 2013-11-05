package com.example.wordfindertwo.core;

public enum SelectionStatus {

	SelectionGood, //new valid word. added to the "found words" list
	SelectionOld, //valid word, but already found. (only if the same word was found at the same place)
	SelectionBad, //valid sequence, but no word.
	SelectionInvalid; //invalid sequence. (multiple use of fields)
	
}
