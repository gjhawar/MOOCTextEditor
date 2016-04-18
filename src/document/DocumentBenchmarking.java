package document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 *
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {


	public static void main(String [] args) throws IOException {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";

	    // The amount of characters to increment each step
	    // You can play around with this
		int increment = 20000;

		// The number of steps to run.
		// You can play around with this.
		int numSteps = 20;

		// THe number of characters to start with.
		// You can play around with this.
		int start = 50000;

		// TODO: Fill in the rest of this method so that it runs two loops
		// and prints out timing results as described in the assignment
		// instructions.
		BufferedWriter bw = new BufferedWriter(new FileWriter("a1.csv"));

		for (int numToCheck = start; numToCheck < numSteps*increment + start;
				numToCheck += increment)
		{
			// numToCheck holds the number of characters that you should read from the
			// file to create both a BasicDocument and an EfficientDocument.

			/* Each time through this loop you should:
			 * 1. Print out numToCheck followed by a tab (\t) (NOT a newline)
			 * 2. Read numToCheck characters from the file into a String
			 *     Hint: use the helper method below.
			 * 3. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates a BasicDocument
			 *     b. Calls fleshScore on this document
			 * 4. Print out the time it took to complete the loop in step 3
			 *      (on the same line as the first print statement) followed by a tab (\t)
			 * 5. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates an EfficientDocument
			 *     b. Calls fleshScore on this document
			 * 6. Print out the time it took to complete the loop in step 5
			 *      (on the same line as the first print statement) followed by a newline (\n)
			 */
			bw.write(numToCheck + "\t");
			String testString = getStringFromFile(textfile, numToCheck);
			long totalTimebd = 0;
			long totalTimeed = 0;

			// For 100 trials of making a BasicDocument and calculating flesch score, total time taken is
			for(int i=0; i<trials; i++){
				long startTimebd = System.nanoTime();
				BasicDocument bd = new BasicDocument(testString);
				bd.getFleschScore();
				long endTimebd = System.nanoTime();
				totalTimebd += ((endTimebd-startTimebd));
			}
			bw.write(totalTimebd/Math.pow(10.0, 9.0) + "\t");

			// For 100 trials of making a EfficientDocument and calculating flesch score, total time taken is
			for(int i=0; i<trials; i++){
				long startTimeed = System.nanoTime();
				EfficientDocument ed = new EfficientDocument(testString);
				ed.getFleschScore();
				long endTimeed= System.nanoTime();
				totalTimeed += ((endTimeed-startTimeed));
			}
			bw.write(totalTimeed/Math.pow(10, 9) + "\n");
		}
		bw.close();

	}

	/** Get a specified number of characters from a text file
	 *
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {

		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		return s.toString();
	}

}
