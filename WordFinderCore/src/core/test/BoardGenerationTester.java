package core.test;

import core.TestDictionary;
import core.gen.BasicSeedGenerator;
import core.gen.ISeedGenerator;

public class BoardGenerationTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ISeedGenerator gen = new BasicSeedGenerator();
		
		String seed = gen.generateRandomSeed(null, new TestDictionary(), 6);
		
		System.out.println(seed);
		
		System.out.println();
		
		for (int i = 0; i < 6; i++) {
			System.out.println(seed.substring(i * 6, i * 6 + 6));
		}

	}

}
