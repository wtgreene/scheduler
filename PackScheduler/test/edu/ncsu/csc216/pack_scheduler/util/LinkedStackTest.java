/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Tests LinkedStack.java
 * 
 * @author Will Greene
 * @param <E> element
 */
class LinkedStackTest<E> {

	/**
	 * Tests LinkedStack constructor.
	 */
	@Test
	void testLinkedStack() {
		assertDoesNotThrow(() -> new LinkedStack<E>(10));
	}

	/**
	 * Tests LinkedStack.push().
	 */
	@Test
	void testPush() {
		LinkedStack<String> a = new LinkedStack<String>(2);
		
		a.push("a");
		
		assertEquals(1, a.size());
		
		a.push("b");

		assertEquals(2, a.size());
		
		assertThrows(IllegalArgumentException.class, () -> a.push("c"));
	}

	/**
	 * Tests LinkedStack.pop().
	 */
	@Test
	void testPop() {
		LinkedStack<String> a = new LinkedStack<String>(10);
		
		assertThrows(EmptyStackException.class, () -> a.pop());
		
		a.push("a");
		a.push("b");

		assertEquals("b", a.pop());
		assertEquals("a", a.pop());
	}

	/**
	 * Tests LinkedStack.isEmpty().
	 */
	@Test
	void testIsEmpty() {
		LinkedStack<String> a = new LinkedStack<String>(10);
		
		assertTrue(a.isEmpty());
		
		a.push("a");
		
		assertFalse(a.isEmpty());
	}

	/**
	 * Tests LinkedStack.size().
	 */
	@Test
	void testSize() {
		LinkedStack<String> a = new LinkedStack<String>(10);
		
		a.push("a");		
		a.push("b");
		
		assertEquals(2, a.size());
		
		a.pop();
		
		assertEquals(1, a.size());
	}

	/**
	 * Tests LinkedStack.setCapacity().
	 */
	@Test
	void testSetCapacity() {
		LinkedStack<String> a = new LinkedStack<String>(10);
		
		a.push("a");
		a.push("b");
		a.push("c");
		
		assertDoesNotThrow(() -> a.setCapacity(3));
		
		assertThrows(IllegalArgumentException.class, () -> a.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> a.setCapacity(2));
	}
}
