package core;

import java.util.ArrayList;

public class WordChecker implements IWordChecker {

	@Override
	public boolean isValidWord(IWord word, ArrayList<IDictionary> dictionaries) {
		String wordString = word.toString();
		for (IDictionary dic : dictionaries) {
			if (dic.getWords().contains(wordString))
				return true;
		}
		return false;
	}

}
