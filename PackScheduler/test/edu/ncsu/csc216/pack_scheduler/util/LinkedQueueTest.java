/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Tests LinkedQueue.java
 * 
 * @author Will Greene
 * @param <E> element
 */
class LinkedQueueTest<E> {

	/**
	 * Tests LinkedQueue constructor.
	 */
	@Test
	void testLinkedQueue() {
		assertDoesNotThrow(() -> new LinkedQueue<E>(10));
	}

	/**
	 * Tests LinkedQueue.enqueue().
	 */
	@Test
	void testEnqueue() {
		LinkedQueue<String> a = new LinkedQueue<String>(2);
		
		a.enqueue("a");
		
		assertEquals(1, a.size());
		
		a.enqueue("b");

		assertEquals(2, a.size());
		
		assertThrows(IllegalArgumentException.class, () -> a.enqueue("c"));
	}

	/**
	 * Tests LinkedQueue.dequeue().
	 */
	@Test
	void testDequeue() {
		LinkedQueue<String> a = new LinkedQueue<String>(10);
		
		assertThrows(EmptyStackException.class, () -> a.dequeue());
		
		a.enqueue("a");
		a.enqueue("b");

		assertEquals("a", a.dequeue());
		assertEquals("b", a.dequeue());
	}

	/**
	 * Tests LinkedQueue.isEmpty().
	 */
	@Test
	void testIsEmpty() {
		LinkedQueue<String> a = new LinkedQueue<String>(10);
		
		assertTrue(a.isEmpty());
		
		a.enqueue("a");
		
		assertFalse(a.isEmpty());
	}

	/**
	 * Tests LinkedQueue.size().
	 */
	@Test
	void testSize() {
		LinkedQueue<String> a = new LinkedQueue<String>(10);
		
		a.enqueue("a");		
		a.enqueue("b");
		
		assertEquals(2, a.size());
		
		a.dequeue();
		
		assertEquals(1, a.size());
	}

	/**
	 * Tests LinkedQueue.setCapacity().
	 */
	@Test
	void testSetCapacity() {
		LinkedQueue<String> a = new LinkedQueue<String>(10);
		
		a.enqueue("a");
		a.enqueue("b");
		a.enqueue("c");
		
		assertDoesNotThrow(() -> a.setCapacity(3));
		
		assertThrows(IllegalArgumentException.class, () -> a.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> a.setCapacity(2));
	}
}
