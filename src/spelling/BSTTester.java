package spelling;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BSTTester{

	BST<Integer> bst;
	BST<Integer> emptybst;

	@Before
	public void setUp(){
		bst = new BST<Integer>();
		emptybst = new BST<Integer>();
		bst.root = new TreeNode<Integer>(20);
		bst.insert(bst.root, 10);
		bst.insert(bst.root, 30);
		bst.insert(bst.root, 5);
		bst.insert(bst.root, 4);
		bst.insert(bst.root, 3);
		bst.insert(bst.root, 25);
		bst.insert(bst.root, 26);
		bst.insert(bst.root, 7);
		bst.insert(bst.root, 8);
		bst.insert(bst.root, 6);
	}

	@Test
	public void testFind(){
		assertEquals(bst.find(bst.root, 2), false);
		assertEquals(bst.find(bst.root, 10), true);
		try{
			bst.find(bst.root, null);
			fail("Data is null");
		} catch(NullPointerException e) {
		}
		assertEquals(emptybst.find(emptybst.root, 1), false);
	}

	@Test
	public void testDelete(){
		assertEquals(bst.delete(bst.root, 1), false);
		assertEquals(bst.delete(bst.root, 6), true);
		try{
			bst.insert(bst.root, null);
			fail("Data is null");
		} catch(NullPointerException e) {
		}
	}

}
