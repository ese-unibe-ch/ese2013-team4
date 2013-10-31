package core.test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Letter;
import core.exceptions.InvalidLetterException;

public class LetterTest {

	@Test
	public void validLetterRetreival() throws InvalidLetterException {
		assertEquals(Letter.A, Letter.getLetter('A'));
		assertEquals(Letter.A, Letter.getLetter('a'));
		assertEquals(Letter.Z, Letter.getLetter('Z'));
		assertEquals(Letter.Z, Letter.getLetter('z'));
		
	}
	
	@Test
	public void testCharRetreival() throws InvalidLetterException {
		assertEquals('A', Letter.getLetter('A').getChar());
		assertEquals('A', Letter.getLetter('a').getChar());
	}
	
	@Test(expected = InvalidLetterException.class)
	public void invalidLetterRetreival() throws InvalidLetterException {
		Letter.getLetter(' ');
	}

}
