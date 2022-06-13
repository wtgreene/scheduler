package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

public class SortedListTest {

	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		// test isEmpty()
		assertTrue(list.isEmpty());
		
		// Test that the list grows by adding at least 11 elements
		// Remember the list's initial capacity is 10
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		list.add("k");
		assertEquals(list.size(), 11);
	}

	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		// Test adding to the front, middle and back of the list
		list.add("a");
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), "a");
		assertEquals(list.get(1), "banana");
		
		list.add("ab");
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), "a");
		assertEquals(list.get(1), "ab");
		assertEquals(list.get(2), "banana");
		
		list.add("c");
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), "a");
		assertEquals(list.get(1), "ab");
		assertEquals(list.get(2), "banana");
		assertEquals(list.get(3), "c");
		
		// Test adding a null element
		assertThrows(NullPointerException.class, () -> list.add(null));
		
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), "a");
		assertEquals(list.get(1), "ab");
		assertEquals(list.get(2), "banana");
		assertEquals(list.get(3), "c");
		
		// Test adding a duplicate element
		assertThrows(IllegalArgumentException.class, () -> list.add("a"));
		
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), "a");
		assertEquals(list.get(1), "ab");
		assertEquals(list.get(2), "banana");
		assertEquals(list.get(3), "c");
	}
	
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here.  Instead this test method should focus on the error 
		// and boundary cases.
		
		// Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		
		// Add some elements to the list
		list.add("a");
		list.add("b");
		list.add("c");
		
		// Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		
		// Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
	}
	
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		
		// Add some elements to the list - at least 4
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		// Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		
		// Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		
		// Test removing a middle element
		list.remove(1);
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), "a");
		assertEquals(list.get(1), "c");
		assertEquals(list.get(2), "d");
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
		
		// Test removing the last element
		list.remove(list.size() - 1);
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), "a");
		assertEquals(list.get(1), "c");
		
		// Test removing the first element
		list.remove(0);
		assertEquals(list.size(), 1);
		assertEquals(list.get(0), "c");
		
		// Test removing the last element
		list.remove(0);
		assertEquals(list.size(), 0);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		// Test indexOf on an empty list
		assertEquals(list.indexOf("a"), -1);
		
		// Add some elements
		list.add("a");		
		list.add("b");		
		list.add("c");
		
		// Test various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(list.indexOf("a"), 0);
		assertEquals(list.indexOf("b"), 1);
		assertEquals(list.indexOf("c"), 2);
		assertEquals(list.indexOf("d"), -1);
		assertEquals(list.indexOf("ab"), -1);
		
		// Test checking the index of null
		assertThrows(NullPointerException.class, () -> list.indexOf(null));
	}
	
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("a");		
		list.add("b");		
		list.add("c");

		// Clear the list
		list.clear();
		
		// Test that the list is empty
		assertTrue(list.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		// Test that the list starts empty
		assertTrue(list.isEmpty());
		
		// Add at least one element
		list.add("a");
		
		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		// Test the empty list case
		assertFalse(list.contains("a"));
		
		// Add some elements
		list.add("a");
		list.add("b");
		list.add("c");
		
		// Test some true and false cases
		assertTrue(list.contains("a"));
		assertTrue(list.contains("b"));
		assertTrue(list.contains("c"));
		assertFalse(list.contains("d"));
	}
	
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		list1.add("c");
		
		list2.add("a");
		list2.add("b");
		list2.add("c");

		list3.add("a");
		list3.add("b");
		list3.add("d");
		
		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		
		assertFalse(list1.equals(list3));
	}
	
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		list1.add("c");
		
		list2.add("a");
		list2.add("b");
		list2.add("c");

		list3.add("a");
		list3.add("b");
		list3.add("d");
		
		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}
}
 