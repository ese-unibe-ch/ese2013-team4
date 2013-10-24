package core.test;

import core.SeedGenerator;
import core.test.TestDictionary;

public class BoardGenerationTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SeedGenerator gen = SeedGenerator.getInstance();
		
		String seed = gen.generateRandomSeed(null, new TestDictionary(), 6);
		
		System.out.println(seed);
		
		System.out.println();
		
		for (int i = 0; i < 6; i++) {
			System.out.println(seed.substring(i * 6, i * 6 + 6));
		}

	}

}
