package core;

import java.util.ArrayList;

public class Word implements WordInterface {

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

}
