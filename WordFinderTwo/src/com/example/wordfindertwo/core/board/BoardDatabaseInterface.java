package com.example.wordfindertwo.core.board;

import com.example.wordfindertwo.core.exceptions.BoardIdAlreadySetException;
import com.example.wordfindertwo.core.exceptions.BoardIdNotSetException;

public interface BoardDatabaseInterface {

	public void setID(long ID) throws BoardIdAlreadySetException;

	/**
	 * 
	 * @return the boards ID code. throws BoardIdNotSetException when hasLegalID evaluates as false
	 */
	public long getID() throws BoardIdNotSetException;
	
	/**
	 * 
	 * @return determines whether or not the board has a legal id (>= 0)
	 */
	public boolean hasLegalID();
	
	/**
	 * 
	 * @return the boards seed. This seed must be compatible with the BoardGeneration over the SeedGenerator
	 */
	public String getSeed();
	
}
