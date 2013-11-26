package com.example.wordfindertwo.core.board;

import java.util.ArrayList;

/**
 * Interface for all Dictionary based interactions of the board
 */
public interface BoardDictionarySupportInterface {
	
	/**
	 * @return the primary dictionary of the Board, if available, the secondary otherwise.
	 */
	public ArrayList<String> getCustomWords();
	
	/**
	 * @return the secondary dictionary of the Board. Is the same one gotten by 'getPrimaryDictionary' when there's no primary one.
	 */
	public int getSystemDictionaryID();
	
	public ArrayList<String> getWordsInBoard();

}
