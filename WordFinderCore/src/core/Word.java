package core;

import java.util.ArrayList;

public class Word implements IWord {

	ArrayList<Letter> letters;
	
	public Word () {
		this.letters = new ArrayList<Letter>();
	}
	
	@Override
	public void setLetterSequence(ArrayList<Letter> letters) {
		this.letters = letters;
	}

	@Override
	public ArrayList<Letter> getLetterSequence() {
		return this.letters;
	}

	@Override
	public int getWordValue() {
		int sum = 0;
		for (Letter letter : this.letters) {
			sum += letter.getValue();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		if (this.letters.isEmpty())
			return "";
		String sequence = "";
		for (Letter letter : letters) {
			sequence += letter.getChar();
		}
		return sequence;
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
