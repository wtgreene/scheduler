/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * ArrayList that implements queue functionality.
 * 
 * @author Will Greene
 * @param <E> element
 */
public class LinkedQueue<E> implements Queue<E> {
	
	/** list */
	private LinkedAbstractList<E> list;
	/** list capacity */
	private int capacity;
	
	/** 
	 * Constructs an ArrayQueue object.
	 * 
	 * @param capacity list capacity
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	/**
	 * Adds the element to the back of the queue.
	 * 
	 * @param element element to add
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	@Override
	public void enqueue(E element) {
		
		if (size() >= capacity) {
			throw new IllegalArgumentException();
		}
		
		list.add(element);
	}

	/**
	 * Removes and returns the element at the front of the queue.
	 * 
	 * @return element at the front of the queue
	 * @throws NoSuchElementException if stack is empty
	 */
	@Override
	public E dequeue() {
		
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		
		return list.remove(0);
	}

	/**
	 * Returns whether the queue is empty.
	 * 
	 * @return true if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the queue's capacity.
	 * 
	 * @param capacity capacity to set
	 * @throws IllegalArgumentException if negative or less than number of elements
	 *                                  in queue
	 */
	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
		this.capacity = capacity;
	}
}
