/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * LinkedAbstractList that implements stack functionality.
 * 
 * @author Will Greene
 * @param <E> element
 */
public class LinkedStack<E> implements Stack<E> {
	
	/** list */
	private LinkedAbstractList<E> list;
	/** list capacity */
	private int capacity;
	
	/**
	 * Constructs a LinkedStack object.
	 * 
	 * @param capacity list capacity
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	/**
	 * Adds an element to the top of the stack.
	 * 
	 * @param element element to add
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	@Override
	public void push(E element) {
		
		if (size() >= capacity) {
			throw new IllegalArgumentException();
		}
		
		list.add(0, element);
	}

	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return element at the top of the stack
	 * @throws EmptyStackException if stack is empty
	 */
	@Override
	public E pop() {
		
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		
		return list.remove(0);
	}

	/**
	 * Returns whether the stack is empty.
	 * 
	 * @return true if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the stack's capacity.
	 * 
	 * @param capacity capacity to set
	 * @throws IllegalArgumentException if negative or less than number of elements
	 *                                  in stack
	 */
	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
		this.capacity = capacity;
	}
}
