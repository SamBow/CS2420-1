package assign9;

import java.io.File;
import java.util.List;

/**
 * A small demonstration of the SpellChecker class.
 * 
 * @author Tim Ellenberger, ellenber
 * @author John Strasser, strasser
 * @author Erin Parker
 * @version 11/05/2015
 */
public class SpellCheckerDemoWithAddingToDict {

	public static void main(String[] args) {

		SpellChecker mySC = new SpellChecker(new File("src/assign9/ref/dictionary.txt"));

		run_spell_check(mySC, "src/assign9/ref/hello_world.txt");
		run_spell_check(mySC, "src/assign9/ref/good_luck.txt");
		// expect Erin and BST to not be in dictionary, add them and run again
		mySC.addToDictionary("ERIN");
		mySC.addToDictionary("bST");
		// now re-run the file
		run_spell_check(mySC, "src/assign9/ref/good_luck.txt");
	}

	private static void run_spell_check(SpellChecker sc, String documentFilename) {

		File doc = new File(documentFilename);
		List<String> misspelledWords = sc.spellCheck(doc);
		if (misspelledWords.size() == 0) {
			System.out.println("There are no misspelled words in file " + doc + ".");
		} else {
			System.out.println("The misspelled words in file " + doc + " are:");
			for (String w : misspelledWords) {
				System.out.println("\t" + w);
			}
		}

	}
}
