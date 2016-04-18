package spelling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;


    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}


	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		TrieNode newNode = root;
		word = word.toLowerCase();
	    char[] charList = word.toCharArray();
	    Boolean newWordAddedFlag = false;

	    for(int i=0; i<charList.length; i++){
	    	newWordAddedFlag = true;
	    	// If first char and the children doesn`t exist that means its a new char
	    	if(i == 0 && newNode.getChild(charList[i])==null){
	    		 newNode = newNode.insert(charList[i]);
	    	}
	    	// If any other char and the children doesn`t exist add them to children and create new node
	    	// also add isWord flag true if last word.
	    	else if(i!=0 && newNode.getChild(charList[i])==null){
	    		newNode = newNode.insert(charList[i]);
	        }
	    	//If char already has children move to next node.
	    	else{
	    		newNode = newNode.getChild(charList[i]);
	    		newWordAddedFlag = false;
	    	}
	    	if(i==charList.length-1){
    			newNode.setEndsWord(true);
    			size++;
	    	}

	    }
	    //If new node was added return true else false
	    if(newNode!=null && newWordAddedFlag)
	    	return true;
	   return false;
	}

	/**
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 * Struggled with coming up with the tree traversal strategy. Used BFS i.e. level based traversal as used for prediction algo
	 */
	public int size()
	{
		TrieNode tempNode = root;
		size = 0;
		Queue<TrieNode> q = new LinkedList<>();
		q.add(tempNode);

		while(!q.isEmpty()){
			tempNode = q.remove();
			if(tempNode == null)
				break;

			if(tempNode.endsWord())
				size++;

			for(char c :tempNode.getValidNextCharacters()){
				q.add(tempNode.getChild(c));
			}
		}

		return size;
	}


	/** Returns whether the string is a word in the trie
	 * Missed the case where if the words is already in the tree like "downhill" and is a word, and if we try to check another word like "downhil" if
	 * I dont add the condition tempNode.endsWord() it would return true coz that partial text is in the tree, but its not a word. */
	@Override
	public boolean isWord(String s)
	{
		s = s.toLowerCase();
		TrieNode tempNode = root;

		// Get all chars in the string
		char[] allChars = s.toCharArray();
		for(char c : allChars){
			System.out.println(c);
			tempNode = tempNode.getChild(c);
			if(tempNode == null)
				break;
			if(tempNode.getText().equals(s) && tempNode.endsWord())
				return true;
		}
		return false;
	}

	/**
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions)
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 TrieNode tempnode = root;
    	 List<String> predictionsList = new ArrayList<>();
    	 // Try to find the stem in the trie
    	 for(char c : prefix.toCharArray()) {
 			 // If the prefix is not equal to the text and there are so nodes left
    		 //System.out.println(c);
    		 if(tempnode!=null && !tempnode.getText().equals(prefix)){
 				tempnode = tempnode.getChild(c);
 			 }
 			 // If their is no entry in the hashmap for the prefix
 			 if(tempnode == null){
 				break;
 			 }
 		 }

    	 Queue<TrieNode> q = new LinkedList<>();
    	 q.add(tempnode);
    	 int currentCompletions = 0;
    	 int count = 0;

    	 // To traverse until queue has node and completions are left
    	 while(!q.isEmpty() && currentCompletions!=numCompletions){
    		 //System.out.println(count);
    		 count++;
    		 tempnode = q.remove();

    		 if(tempnode == null)
    			 break;

    		 if(tempnode.endsWord()){
    			 predictionsList.add(tempnode.getText());
    			 System.out.println(tempnode.getText());
    			 currentCompletions++;
    			 System.out.println(currentCompletions);
    		 }

    		 //System.out.println("valid next char are : " +tempnode.getValidNextCharacters());
    		 Iterator<Character> it = tempnode.getValidNextCharacters().iterator();
    		 while (it.hasNext()) {
    		        char eachChildChar = (char)it.next();
    		        q.add(tempnode.getChild(eachChildChar));
    		}
    	 }
 		 return predictionsList;

     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}

 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null)
 			return;

 		System.out.println(curr.getText() + " " + curr.endsWord());

 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}


 	public static void main(String args[]){
 		AutoCompleteDictionaryTrie smallDict = new AutoCompleteDictionaryTrie();
		System.out.println(smallDict.addWord("downhill"));

		smallDict.printTree();
		System.out.println(smallDict.isWord("downhille"));
 	}



}