package com.example.wordfindertwo.core.exceptions;

public class BoardIdNotSetException extends Exception {

	private static final long serialVersionUID = -5694366432300507618L;

	public BoardIdNotSetException () {
		super();
	}
	
	public BoardIdNotSetException (String msg) {
		super(msg);
	}
	
}
