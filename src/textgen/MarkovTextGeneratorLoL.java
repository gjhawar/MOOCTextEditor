package textgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}


	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		//String[] splittedSourceText = sourceText.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
		String[] splittedSourceText = sourceText.split("\\s+");
		starter = splittedSourceText[0];
		String prevWord = starter;
		int loopCount = 0;

		for(String w : splittedSourceText){
			boolean prevWordFoundFlag = false;
			loopCount++;
			// Skip the first word
			if(loopCount == 1)
				continue;
			// Check if prevWord is a node in the list
			for(ListNode listNode : wordList){
				String currentWord = listNode.getWord();
				if(prevWord.equals(currentWord)){
					listNode.addNextWord(w);
					prevWordFoundFlag = true;
					break;
				}
			}

			// if not found add a node with prevWord and add w as the next word for the prevNode
			if(!prevWordFoundFlag){
				// If last word does not have a previous node already in the LOL, create a new one with next node being starter
				ListNode l = new ListNode(prevWord);
				l.addNextWord(w);
				wordList.add(l);
			}
			prevWord = w;
		}

		boolean prevWordFoundFlag = false;
		String lastWord = splittedSourceText[splittedSourceText.length-1];
		// If last index also check if last word is there in the LOL if yes add starter as its next word.
		for(ListNode listNode : wordList){
			String currentWord = listNode.getWord();
			if(lastWord.equals(currentWord)){
				// Add w
				listNode.addNextWord(starter);
				prevWordFoundFlag = true;
				break;
			}
		}
		if(!prevWordFoundFlag){
			ListNode lastNode = new ListNode(lastWord);
			lastNode.addNextWord(starter);
			wordList.add(lastNode);
		}

	}


	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {

		if(wordList.size() == 0)
			return "";
		if(numWords == 0)
			return "";

		String currWord = starter;
		String output = "";
		output+=currWord;
		numWords--;

		while(numWords>0){
			String w = "";
			for(ListNode listNode : wordList){
				if(currWord.equals(listNode.getWord())){
					w = listNode.getRandomNextWord(rnGenerator);
					output += " ";
					output += w;
					currWord = w;
					numWords--;
					break;
				}
			}
		}
		//System.out.println(output.split("\\s+").length);
		return output;
	}


	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.


	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(1));
		String textString = "hi hi hello hi hi hi there hi leo hi leo.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(500));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator)
	{
		// The random number generator should be passed from
	    // the MarkovTextGeneratorLoL class
		return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->>";
		}
		toReturn += "\n";
		return toReturn;
	}

}


