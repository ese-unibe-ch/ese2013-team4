package com.example.wordfindertwo.core.exceptions;

public class BoardIdAlreadySetException extends Exception {

	private static final long serialVersionUID = -4867418077522522254L;

	public BoardIdAlreadySetException () {
		super();
	}
	
	public BoardIdAlreadySetException (String msg) {
		super(msg);
	}
	
}
