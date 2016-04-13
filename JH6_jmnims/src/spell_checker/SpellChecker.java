package spell_checker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SpellChecker
{
	private final String DICTIONARY_FILE = "dictionary.txt"; 

	// We could have also used TreeSet for the dictionary 
	private HashSet<String> dictionary = new HashSet<String>();

	private TreeSet<String> bad_spellings = new TreeSet<String>();

	private File dictionaryFile = new File(DICTIONARY_FILE);

	public SpellChecker() throws DictionaryFileNotFoundException
	{
		// Add all of the words from "dictionary.txt" to the dictionary HashSet
		Scanner dictScanner = null;
		try
		{
			dictScanner = new Scanner(dictionaryFile);

			// We assume each dictionary word is on its own line.
			while (dictScanner.hasNextLine())
				dictionary.add(dictScanner.nextLine().toLowerCase());
		}
		catch (FileNotFoundException e)
		{
			throw new DictionaryFileNotFoundException();
		}
		finally 
		{
			if (dictScanner != null)
				dictScanner.close();
		}
	}

	public void checkSpelling(String fileName) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File(fileName)); // can throw FileNotFoundException
		Scanner userInput = new Scanner(System.in);
		String line = "", 
				word = "", 
				wordTrimmed = "",
				result = "";

		System.out.println("======== Spell checking " + fileName + " =========");

		// Clear miss_spelled_words
		bad_spellings.clear();

		while (scan.hasNextLine())
		{
			line = scan.nextLine();
			StringTokenizer st = new StringTokenizer(line, " \t,.;:-%'\"");

			while (st.hasMoreTokens())
			{
				word = st.nextToken().toLowerCase().trim();
				if (
						!isFirstCharAlphabet(word) || 
						dictionary.contains(word) || 
						bad_spellings.contains(word))
					continue;

				wordTrimmed = trimTrailing_S(word);
				if (dictionary.contains(wordTrimmed) || bad_spellings.contains(wordTrimmed))
					continue;
				do
				{
					System.out.println("\"" + word + "\"" + " was not found in the dictionary. Do you want to add it (y/n): ");
					result = userInput.nextLine().toLowerCase().trim();

					// Eclipse bug? The program wouldn't break out of this loop until I added these lines in order to debug.
					// After adding them, it worked fine. After commenting them, it continued working.
					// I left them in, in case the problem occurred again. I'm really not sure what the issue was.
					//System.out.println("Is equal to \"y\": " + (result.equals("y") ? "yes" : "no"));
					//System.out.println("Is equal to \"n\": " + (result.equals("n") ? "yes" : "no"));
				} while (!result.equals("y") && !result.equals("n"));

				if (result.equals("y"))
				{
					dictionary.add(word);
					System.out.println("\"" + word + "\"" + " added to dictionary.");
				}
				else
				{
					bad_spellings.add(word);
					System.out.println("\"" + word + "\"" + " added to mis-spelled words list.");
				}
			}
		}
		
		// Can't be null if we made it here.
		scan.close();
		userInput.close();
	}

	private boolean isFirstCharAlphabet(String word)
	{
		if (word.isEmpty())
			return false;
		return Character.isLetter(word.charAt(0));
	}

	private String trimTrailing_S(String word)
	{
		if (word.isEmpty())
			return word;
		if (word.endsWith("s"))
			return word.substring(0, word.length() - 1);
		return word;
	}



	// Print out the misspelled words
	public void printBadSpellings()
	{
		int counter = 1;
		System.out.println("\nThere were " + bad_spellings.size() + " improperly spelled words found in the file.");
		for (String elem : bad_spellings)
		{
			System.out.println(String.format("%03d", counter) + ": " + elem);
			counter++;
		}
		System.out.println();
	}


	public static void main(String[] args) 
	{
		System.out.println(System.getProperty("user.dir"));
		try 
		{
			// New SpellChecker object
			SpellChecker SpellChecker = new SpellChecker();

			// Check the spelling of each file given in the launch arguments
			for (int i = 0; i < args.length; i++)
			{
				// SpellChecker is [this] object
				SpellChecker.checkSpelling(args[i]);
				SpellChecker.printBadSpellings();
			}            
		}
		catch (DictionaryFileNotFoundException e)
		{
			// No dictionary. Pointless to continue.
			System.out.println(e);
			System.out.println("Dictionary file not found. Closing program.");
			return;
		}
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
		}
	}

	private class DictionaryFileNotFoundException extends FileNotFoundException	
	{
		// To quiet down warnings.
		private static final long serialVersionUID = -3978970491321950320L;

		public DictionaryFileNotFoundException()
		{
			super();
		}

		public DictionaryFileNotFoundException(String msg)
		{
			super(msg);
		}
	}
}

