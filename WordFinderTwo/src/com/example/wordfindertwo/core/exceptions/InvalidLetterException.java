package com.example.wordfindertwo.core.exceptions;

public class InvalidLetterException extends Exception {

	private static final long serialVersionUID = 1989938769463317852L;

	public InvalidLetterException() {
		super();
	}

	public InvalidLetterException(String msg) {
		super(msg);
	}
}
