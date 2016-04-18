package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary
{

	private LinkedList<String> dict;
	private int size;

    public DictionaryLL(){
		this.dict = new LinkedList<>();
		size = 0;
	}


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// Check if the word already exist in the dictionary
    	for(String wordInDict : dict){
    		if(wordInDict.equals(word.toLowerCase()))
    			return false;
    	}
    	// If if doesnt exist add it
    	dict.add(word.toLowerCase());
    	size++;
    	return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return size;
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	for(String wordInDict : dict){
    		if(wordInDict.equals(s.toLowerCase()))
    			return true;
    	}
        return false;
    }


}
