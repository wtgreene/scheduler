/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Tests ArrayStack.java
 * 
 * @author Will Greene
 * @param <E> element
 */
class ArrayStackTest<E> {

	/**
	 * Tests ArrayStack constructor.
	 */
	@Test
	void testArrayStack() {
		assertDoesNotThrow(() -> new ArrayStack<E>(10));
	}

	/**
	 * Tests ArrayStack.push().
	 */
	@Test
	void testPush() {
		ArrayStack<String> a = new ArrayStack<String>(2);
		
		a.push("a");
		
		assertEquals(1, a.size());
		
		a.push("b");

		assertEquals(2, a.size());
		
		assertThrows(IllegalArgumentException.class, () -> a.push("c"));
	}

	/**
	 * Tests ArrayStack.pop().
	 */
	@Test
	void testPop() {
		ArrayStack<String> a = new ArrayStack<String>(10);
		
		assertThrows(EmptyStackException.class, () -> a.pop());
		
		a.push("a");
		a.push("b");

		assertEquals("b", a.pop());
		assertEquals("a", a.pop());
	}

	/**
	 * Tests ArrayStack.isEmpty().
	 */
	@Test
	void testIsEmpty() {
		ArrayStack<String> a = new ArrayStack<String>(10);
		
		assertTrue(a.isEmpty());
		
		a.push("a");
		
		assertFalse(a.isEmpty());
	}

	/**
	 * Tests ArrayStack.size().
	 */
	@Test
	void testSize() {
		ArrayStack<String> a = new ArrayStack<String>(10);
		
		a.push("a");		
		a.push("b");
		
		assertEquals(2, a.size());
		
		a.pop();
		
		assertEquals(1, a.size());
	}

	/**
	 * Tests ArrayStack.setCapacity().
	 */
	@Test
	void testSetCapacity() {
		ArrayStack<String> a = new ArrayStack<String>(10);
		
		a.push("a");
		a.push("b");
		a.push("c");
		
		assertDoesNotThrow(() -> a.setCapacity(3));
		
		assertThrows(IllegalArgumentException.class, () -> a.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> a.setCapacity(2));
	}
}
