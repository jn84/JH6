package spell_checker;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SpellChecker {

	// We could have also used TreeSet for the dictionary 
	private HashSet<String> dictionary = new HashSet<String>();

	private TreeSet<String> bad_spellings = new TreeSet<String>();

	public SpellChecker() throws FileNotFoundException
	{
		// Add all of the words from "dictionary.txt" to the dictionary HashSet    }

		public void checkSpelling(String fileName) throws FileNotFoundException
		{
			System.out.println("======== Spell checking " + fileName + " =========");

			// Clear miss_spelled_words
			bad_spellings.clear();

			// Read in each line from "fileName"
			// For each line, break the line into words using the following StringTokenizer
			StringTokenizer st = new StringTokenizer(line, " \t,.;:-%'\"");

			// lower case each word obtained from the StringTokenizer
			// skip word if the first character is not a letter

			// Skip over word if it can be found in either dictionary, or miss_spelled_words
			// If word ends with 's', then try the singular version of the word in the dictionary and miss_spelled_words ... skip if found

			// Print line containing miss-spelled word(make sure you only print it once if multiple miss-spelled words are found on this line)

			// Ask the user if he wants the word added to the dictionary or the miss-spelled words and add word as specified by the user



		}

		public void printBadSpellings()
		{
			int counter = 1;
			System.out.println("\nThere were" + bad_spellings.size() + "improperly spelled words found in the file.");
			for (String elem : bad_spellings)
			{
				System.out.println(String.format("%04d", counter) + ": " + elem);
				counter++;
			}
			System.out.println();
		}


		public static void main(String[] args) {

			try 
			{
				SpellChecker SpellChecker = new SpellChecker();

				for (int i = 0; i < args.length; i++)
				{
					SpellChecker.checkSpelling(args[i]);
					SpellChecker.printBadSpellings();
				}            
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println(e);
			}


		}

	}

