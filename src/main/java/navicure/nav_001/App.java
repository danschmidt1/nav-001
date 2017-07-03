package navicure.nav_001;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;

import com.google.common.base.Joiner;

import navicure.nav_001.letter.Letter;

/**
 * Using any paragraph of your choosing, count the number of occurrences of each
 * letter. Display the results in the order of each letter's first occurrence in
 * the paragraph, and again alphabetically.
 *
 */
public class App {

	public static void main(String[] args) {
		try {
			if (args == null || args.length != 2) {
				System.out.println(" enter \"0 {fileName}\" for question 1 (paragraph)"
						+ " + \"1 {fileName}\" for question 2 (primes)");
			} else if ("0".equals(args[0])) {
				doParagraphQuestion(args[1]);
			} else {
				doPrimeNumberQuestion(args[1]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + args[1] + " was not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void doPrimeNumberQuestion(String fileName) throws FileNotFoundException, IOException {
		// Using as input the path to a file containing one integer per line,
		// for each integer in the file output a comma-delimited list of the
		// integer's prime factors
		File theNumberFile = new File(fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(theNumberFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.matches("^-?\\d+$")) {
					System.out.println("Prime factors of " + line + " are : " + primeFactorsBig(new BigInteger(line)));
				} else {
					System.out.println("Line " + line + " contains non-numerical characters");
				}
			}
		}
	}

	/**
	 * Decided to go with a BigInteger implementation, just in case the
	 * definition of "Integer" in the problem was a loose one
	 * 
	 * @param theNumber
	 * @return
	 */
	private static String primeFactorsBig(BigInteger theNumber) {

		BigInteger currentNumber = theNumber;
		List<BigInteger> factors = new ArrayList<BigInteger>();
		// per my research this is the fastest way to do this
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(currentNumber.divide(i)) < 1; i = i
				.add(BigInteger.valueOf(1))) {
			while (currentNumber.mod(i) == BigInteger.valueOf(0)) {
				factors.add(i);
				currentNumber = currentNumber.divide(i);
			}
		}
		if (currentNumber.compareTo(BigInteger.valueOf(1)) > 0) {
			factors.add(currentNumber);
		}
		return CollectionUtils.isEmpty(factors) ? "" : Joiner.on(", ").join(factors);
	}

	public static void doParagraphQuestion(String fileName) throws IOException {
		Map<Character, Letter> theParagraphsLetters = new TreeMap<>();
		String theParagrah = FileUtils.readFileToString(new File(fileName));
		Letter letter = null;
		for (int i = 0; i < theParagrah.length(); i++) {
			// Choosing to consider a lower case and upper case as the same
			// letter
			Character c = Character.toUpperCase(theParagrah.charAt(i));
			if (Character.isLetter(c)) {
				Letter existingLetter = theParagraphsLetters.get(c);
				if (existingLetter == null) {
					letter = new Letter(c, i + 1);
					theParagraphsLetters.put(c, letter);
				} else {
					existingLetter
							.setNumberOfOccurancesInParagraph(existingLetter.getNumberOfOccurancesInParagraph() + 1);
				}
			}
		} // for
		List<Letter> letters = new ArrayList<>(theParagraphsLetters.values());
		// order of appearance, use the Letter classes comparable
		Collections.sort(letters);
		System.out.println("\n ***************************\n Order By First Occurance \n ***************************");
		for (Letter pletter : letters) {
			System.out.println("Letter: " + pletter + " " + pletter.getNumberOfOccurancesInParagraph() + " occurances");
		}
		System.out.println("\n ***************************\n Order Alphabetically \n ***************************");
		// Alphabetical use the natural ordering of key values, provided by the
		// treeset
		theParagraphsLetters.keySet().stream().forEach(p -> {
			System.out.println("Letter: " + p + " " + theParagraphsLetters.get(p).getNumberOfOccurancesInParagraph()
					+ " occurances");
		});

	}
}
