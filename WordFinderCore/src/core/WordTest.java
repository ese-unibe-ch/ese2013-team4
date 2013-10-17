package core;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Word Class.
 * @author $Author: Team4 $
 */

public class WordTest {
	private Word word;
	private ArrayList<Letter> letter_list;
	int wordcount;
	
	@Before
	public void setUp() throws Exception {
		this.word = new Word();
		letter_list = new ArrayList<Letter>();
		letter_list.add(Letter.A);
		letter_list.add(Letter.B);
		letter_list.add(Letter.C);
		this.wordcount = Letter.A.getValue() + Letter.B.getValue() + Letter.C.getValue();
	}
	
	
	/**
	 * Test the getters and setters.
	 */
	
	@Test
	public void setLetterSequenceTest() {
		assertTrue(this.word.getLetterSequence().isEmpty());
		this.word.setLetterSequence(this.letter_list);
		assertEquals(this.letter_list, this.word.getLetterSequence());
		
	}
	
	/**
	 * Test if the value of a word is calculated Correctly.
	 */
	
	@Test
	public void getWordValueTest() {
		assertTrue(this.word.getLetterSequence().isEmpty());
		this.word.setLetterSequence(this.letter_list);
		assertEquals(this.wordcount, this.word.getWordValue());
		
	}
	
}
