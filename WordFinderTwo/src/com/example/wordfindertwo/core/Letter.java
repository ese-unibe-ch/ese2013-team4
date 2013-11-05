package com.example.wordfindertwo.core;

import com.example.wordfindertwo.core.exceptions.InvalidLetterException;

public enum Letter {
	A (1, 'A'),
	B (3, 'B'),
	C (3, 'C'),
	D (2, 'D'),
	E (1, 'E'),
	F (4, 'F'),
	G (2, 'G'),
	H (4, 'H'),
	I (1, 'I'),
	J (8, 'J'), 
	K (5, 'K'),
	L (1, 'L'),
	M (3, 'M'),
	N (1, 'N'),
	O (1, 'O'),
	P (3, 'P'),
	Q (10,'Q'),
	R (1, 'R'), 
	S (1, 'S'), 
	T (1, 'T'),
	U (1, 'U'), 
	V (4, 'V'),
	W (4, 'W'),
	X (8, 'X'),
	Y (4, 'Y'),
	Z (10,'Z');
	
	private final int value;
	private final char character;
	
	private Letter (int value, char character) {
		this.value = value;
		this.character = character;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public char getChar() {
		return this.character;
	}
	
	public static Letter[] getLetterList() {
		return new Letter[]{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
	}
	
	public static Letter getLetter(char character) throws InvalidLetterException {
		character = Character.toUpperCase(character);
		Letter[] list = getLetterList();
		for (Letter letter : list) {
			if (letter.getChar() == character)
				return letter;
		}
		throw new InvalidLetterException();
	}
}
