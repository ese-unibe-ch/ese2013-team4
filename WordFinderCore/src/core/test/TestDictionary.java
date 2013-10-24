package core.test;

import java.util.ArrayList;

import core.ADictionary;

/**
 * A basic dictionary used for testing.
 * 
 * Contains a list of a few English words.
 * 
 * @author Andreas Waelchli <andreas.waelchli@me.com>
 */
public class TestDictionary extends ADictionary {

	public TestDictionary() {
		super("Test", 0); //Dictionary Name is "Test"
	}
	
	@Override
	public ArrayList<String> generateWordList(String title) {
		ArrayList<String> words = new ArrayList<String>();
		// -- INSERT WORDS HERE --
		words.add("Test");
		words.add("English");
		words.add("Bridge");
		words.add("Telephone");
		words.add("Joystick");
		words.add("Smartphone");
		words.add("Rocket");
		words.add("Airplane");
		words.add("Bottle");
		words.add("Water");
		words.add("Speaker");
		words.add("Glass");
		words.add("Spaceship");
		words.add("Astronaut");
		words.add("Building");
		words.add("Tower");
		words.add("swimming");
		words.add("documenting");
		words.add("coding");
		words.add("writing");
		words.add("Ink");
		words.add("Pen");
		words.add("Sharpener");
		words.add("Shaver");
		words.add("pick");
		words.add("Bird");
		words.add("Circle");
		words.add("Square");
		words.add("Box");
		words.add("Bowl");
		words.add("Cable");
		words.add("Paper");
		words.add("Die");
		words.add("Monster");
		words.add("Women");
		words.add("Fly");
		words.add("Incubation");
		words.add("Infection");
		words.add("Hypnosis");
		words.add("Man");
		words.add("Chest");
		words.add("Axe");
		words.add("Wire");
		// -- END OF WORD LIST --
		return words;
	}

}
