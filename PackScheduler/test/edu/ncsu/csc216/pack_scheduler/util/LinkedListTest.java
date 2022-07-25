/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Tests LinkedList.java
 * 
 * @author Will Greene
 */
public class LinkedListTest {
	
	/**
	 * Tests LinkedList constructor.
	 */
	@Test
	public void testLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests LinkedList.add().
	 */
	@Test
	public void testAdd() {
		LinkedList<String> list = new LinkedList<String>();
		
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
	 * Test LinkedList.growArray().
	 */
	@Test
	public void testGrowArray() {
		LinkedList<String> list = new LinkedList<String>();
		
		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		list.add(3, "d");
		list.add(4, "e");
		list.add(5, "f");
		list.add(6, "g");
		list.add(7, "h");
		list.add(8, "i");
		list.add(9, "j");
		
		assertEquals(10, list.size());
		
		list.add(10, "k");
		
		assertEquals(11, list.size());
		assertEquals("k", list.get(10));
	}
	
	/**
	 * Test LinkedList.remove().
	 */
	@Test
	public void testRemove() {
		LinkedList<String> list = new LinkedList<String>();
		
		// remove to yield empty list
		list.add(0, "a");
		list.remove(0);
		assertEquals(0, list.size());
		
//		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0)); // size double-check
		
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
		
//		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3)); // size double-check
		
		// remove from middle of list
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("c", list.get(1));
		
//		assertThrows(IndexOutOfBoundsException.class, () -> list.get(2)); // size double-check
		
		// remove from front of list
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("c", list.get(0));
		
//		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1)); // size double-check
		
//		LinkedList<String> fruit = new LinkedList<String>();
//		
//		fruit.add(0, "orange");
//		fruit.add(1, "banana");
//		fruit.add(2, "apple");
//		fruit.add(3, "kiwi");
//		
//		fruit.remove(1);
//		
//		assertEquals("apple", fruit.get(2));
	}
	
	/**
	 * Test LinkedList.set().
	 */
	@Test
	public void testSet() {
		LinkedList<String> list = new LinkedList<String>();

		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		
//		assertThrows(NullPointerException.class, () -> list.set(0, null)); // not sure if I need to implement this functionality
		assertThrows(IllegalArgumentException.class, () -> list.set(0, "b")); // duplicate
		assertThrows(NullPointerException.class, () -> list.set(3, "d"));
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
	 * Test LinkedList.size().
	 */
	@Test
	public void testSize() {
		LinkedList<String> list = new LinkedList<String>();

		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		
		assertEquals(3, list.size());
	}
}
