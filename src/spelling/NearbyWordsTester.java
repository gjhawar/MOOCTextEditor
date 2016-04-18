package spelling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class NearbyWordsTester {

	Dictionary empty_dict;
	Dictionary non_empty_dict;

	NearbyWords empty_w;
	NearbyWords non_empty_w;

	List<String> empty_l;
	List<String> non_empty_l;

	@Before
	public void setUp(){
		empty_dict = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(empty_dict, "data/dict.txt");
		empty_w = new NearbyWords(empty_dict);
		empty_l = new ArrayList<String>();

		non_empty_dict = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(non_empty_dict, "data/dict.txt");
		non_empty_w = new NearbyWords(non_empty_dict);
		non_empty_l = new ArrayList<String>();

	}

	@Test
	public void testInsert(){
		empty_w.insertions("i", empty_l, true);
		System.out.println("Insert1 : One away word Strings for for i are:");
	    System.out.println(empty_l+"\n");
	    assertTrue(empty_l.contains("bi"));
	    assertEquals(17, empty_l.size());

	    non_empty_w.insertions("i", non_empty_l, false);
		System.out.println("Insert2 : One away word Strings for for i are:");
	    System.out.println(non_empty_l+"\n");
	    assertTrue(non_empty_l.contains("ai"));
	    assertEquals(51, non_empty_l.size());
	}

	@Test
	public void testSubstitute(){
		empty_w.subsitution("i", empty_l, true);
		System.out.println("Subs1 : One away word Strings for for i are:");
	    System.out.println(empty_l+"\n");
	    assertEquals(25, empty_l.size());

	    non_empty_w.subsitution("i", non_empty_l, false);
		System.out.println("Subs 2: One away word Strings for for i are:");
	    System.out.println(non_empty_l+"\n");
	    assertTrue(non_empty_l.contains("a"));
	    assertEquals(25, non_empty_l.size());

	}

	@Test
	public void testDelete(){
		empty_w.deletions("i", empty_l, true);
		System.out.println("Delete1 : One away word Strings for for i are:");
	    System.out.println(empty_l+"\n");
	    assertEquals(0, empty_l.size());

	    non_empty_w.deletions("i", non_empty_l, false);
		System.out.println("Delete2 : One away word Strings for for i are:");
	    System.out.println(non_empty_l+"\n");
	    //assertTrue(non_empty_l.contains("ac"));
	    assertEquals(1, non_empty_l.size());

	}

}
