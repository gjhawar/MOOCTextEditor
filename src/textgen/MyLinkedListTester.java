/**
 *
 */
package textgen;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 * All the tests run independently.
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * This initializes the test setup
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		assertEquals("Size of list", 0, shortList.size);
	    shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}


	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		assertEquals(emptyList.size, 0);
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}


		// test short list, first contents, then out of bounds
		assertEquals("Size of shortlist", shortList.size, 2);
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

	}


	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		try{
			a = emptyList.remove(0);
			fail("Empty list");
		} catch(IndexOutOfBoundsException e){

		}
		try{
			a = list1.remove(-1);
			fail("Index too low");
		} catch(IndexOutOfBoundsException e){

		}

	}

	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		list1.add(10);
		assertEquals("Size of list1", 4, list1.size());
		assertEquals("Last element of list1", (Integer)10, list1.get(3));
		try{
			list1.add(null);
			fail("Element is null");
		} catch(NullPointerException e){

		}
	}


	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size of empty list", 0, emptyList.size());
		assertEquals("Size of short list", 2, shortList.size());
		assertEquals("Size of longer list", 10, longerList.size());
		assertEquals("Size of list1", 3, list1.size());
	}



	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        list1.add(1, 64);
        assertEquals("Element at index 1", (Integer)64, list1.get(1));
        assertEquals("Element at index 2", (Integer)21, list1.get(2));
        list1.add(2, 22);
        assertEquals("Element at index 2", (Integer)22, list1.get(2));
        assertEquals("Element at index 3", (Integer)21, list1.get(3));
        emptyList.add(0, 1);
        assertEquals("Element at index 0", (Integer)1, emptyList.get(0));
        try{
			list1.add(-1, 100);
			fail("Index too low");
		} catch(IndexOutOfBoundsException e){

		}
        try{
			list1.add(100, 100);
			fail("Index too high");
		} catch(IndexOutOfBoundsException e){

		}

        try{
			list1.add(0, null);
			fail("Element is null");
		} catch(NullPointerException e){

		}

	}

	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try{
			list1.set(-1, 100);
			fail("Not a vaid index for set");
		} catch(IndexOutOfBoundsException e){

		}

		try{
			list1.set(10, 100);
			fail("Not a vaid index for set");
		} catch(IndexOutOfBoundsException e){

		}

		try{
			list1.set(0, null);
		} catch(NullPointerException e){

		}

	    list1.set(0, 165);
	    list1.set(1, 121);
	    list1.set(2, 142);
	    assertEquals("Element at index 0", (Integer)165, list1.get(0));
	    assertEquals("Element at index 1", (Integer)121, list1.get(1));
	    assertEquals("Element at index 2", (Integer)142, list1.get(2));

	}

}
