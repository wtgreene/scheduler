/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Tests LinkedAbstractList.java
 * 
 * @author Will Greene
 */
public class LinkedAbstractListTest {
	
	// IMPLEMENT CAPACITY TESTS
	
	/**
	 * Tests LinkedAbstractList constructor.
	 */
	@Test
	public void testLinkedAbstractList() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests LinkedAbstractList.add().
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		
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
		
		// capacity test
		
		LinkedAbstractList<String> fruit = new LinkedAbstractList<String>(3);
		
		fruit.add(0, "orange");
		fruit.add(1, "banana");
		fruit.add(2, "apple");
		
		assertThrows(IllegalArgumentException.class, () -> fruit.add(3, "kiwi"));
	}
	
	/**
	 * Test LinkedAbstractList.remove().
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		
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
		assertEquals(list.remove(3), "d");
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3)); // size double-check
		
		// remove from middle of list
		assertEquals(list.remove(1), "b");
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("c", list.get(1));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(2)); // size double-check
		
		// remove from front of list
		assertEquals(list.remove(0), "a");
		assertEquals(1, list.size());
		assertEquals("c", list.get(0));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1)); // size double-check
		
		LinkedAbstractList<String> fruit = new LinkedAbstractList<String>(10);
		
		fruit.add(0, "orange");
		fruit.add(1, "banana");
		fruit.add(2, "apple");
		fruit.add(3, "kiwi");
		
		assertEquals(fruit.remove(1), "banana");
		
		assertEquals("kiwi", fruit.get(2));
	}
	
	/**
	 * Test LinkedAbstractList.set().
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);

		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		
		assertThrows(NullPointerException.class, () -> list.set(0, null));
		assertThrows(IllegalArgumentException.class, () -> list.set(0, "b")); // duplicate
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "d"));

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
	 * Test LinkedAbstractList.get().
	 */
	@Test
	public void testGet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);

		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
	}
	
	/**
	 * Test LinkedAbstractList.size().
	 */
	@Test
	public void testSize() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);

		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		
		assertEquals(3, list.size());
	}
}
