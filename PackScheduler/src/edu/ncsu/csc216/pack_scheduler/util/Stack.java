/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Stack behavior.
 * 
 * @author Will Greene
 * @param <E> Element
 */
public interface Stack<E> {

	/**
	 * Adds an element to the top of the stack.
	 * 
	 * @param element element to add
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	void push(E element);

	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return element at the top of the stack
	 * @throws EmptyStackException if stack is empty
	 */
	E pop();

	/**
	 * Returns whether the stack is empty.
	 * 
	 * @return true if empty, false if not
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack
	 */
	int size();

	/**
	 * Sets the stack's capacity.
	 * 
	 * @param capacity capacity to set
	 * @throws IllegalArgumentException if negative or less than number of elements
	 *                                  in stack
	 */
	void setCapacity(int capacity);
}
