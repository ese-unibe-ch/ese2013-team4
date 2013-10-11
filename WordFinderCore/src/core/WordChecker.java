package core;

import java.util.ArrayList;

public class WordChecker implements WordCheckerInterface {

	@Override
	public boolean isValidWord(WordInterface word,
			ArrayList<DictionaryInterface> dictionaries) {
		String wordString = word.toString();
		for(DictionaryInterface dic : dictionaries) {
			if (dic.containsWord(wordString))
				return true;
		}
		return false;
	}

}
