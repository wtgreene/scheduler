/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Tests LinkedListRecursiveRecursive.java
 * 
 * @author Will Greene
 */
public class LinkedListRecursiveTest {

	/**
	 * Tests LinkedListRecursive constructor.
	 */
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests LinkedListRecursive.add().
	 */
	@Test
	public void testAdd() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		assertThrows(NullPointerException.class, () -> list.add(0, null));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "a"));
		
		// add to empty list
		list.add(0, "b");
		assertEquals(1, list.size());
		assertEquals("b", list.get(0));
		
		assertThrows(IllegalArgumentException.class, () -> list.add(1, "b")); // duplicate
		
		// add to end of list
		list.add(1, "d");
		assertEquals(2, list.size());
		assertEquals("b", list.get(0));
		assertEquals("d", list.get(1));
		
		// add to middle of list
		list.add(1, "c");
		assertEquals(3, list.size());
		assertEquals("b", list.get(0));
		assertEquals("c", list.get(1));
		assertEquals("d", list.get(2));
		
		// add to front of list
		list.add(0, "a");
		assertEquals(4, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		assertEquals("d", list.get(3));
	}
	
	/**
	 * Test LinkedListRecursive.remove().
	 */
	@Test
	public void testRemove() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
				
		// remove to yield empty list
		list.add(0, "a");
		list.remove(0);
		assertEquals(0, list.size());
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0)); // size double-check
		
		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		list.add(3, "d");
		
		// remove from end of list
		list.remove(3);
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3)); // size double-check
		
		// remove from middle of list
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("c", list.get(1));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(2)); // size double-check
		
		// remove from front of list
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("c", list.get(0));
		
		LinkedListRecursive<String> list2 = new LinkedListRecursive<String>();

		list2.add("a");
		list2.remove("a");
		assertEquals(0, list2.size());
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1)); // size double-check
		
		LinkedListRecursive<String> fruit = new LinkedListRecursive<String>();
		
		fruit.add(0, "orange");
		fruit.add(1, "banana");
		fruit.add(2, "apple");
		fruit.add(3, "kiwi");
		
		fruit.remove(1);
		
		assertEquals("kiwi", fruit.get(2));
	}
	
	/**
	 * Test LinkedListRecursive.set().
	 */
	@Test
	public void testSet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();

		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		
//		assertThrows(NullPointerException.class, () -> list.set(0, null));
		assertThrows(IllegalArgumentException.class, () -> list.set(0, "b")); // duplicate
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "d"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(4, "d"));

		list.set(0, "A");
		assertEquals("A", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		
		list.set(1, "B");
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("c", list.get(2));
		
		list.set(2, "C");
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
	}
	
	/**
	 * Test LinkedListRecursive.size().
	 */
	@Test
	public void testSize() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();

		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		
		assertEquals(3, list.size());
	}
}
