/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Queue behavior.
 * 
 * @author Will Greene
 * @param <E> Element
 */
public interface Queue<E> {
	
	/**
	 * Adds the element to the back of the queue.
	 * 
	 * @param element element to add
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	void enqueue(E element);

	/**
	 * Removes and returns the element at the front of the queue.
	 * 
	 * @return element at the front of the queue
	 * @throws NoSuchElementException if queue is empty
	 */
	E dequeue();

	/**
	 * Returns whether the queue is empty.
	 * 
	 * @return true if empty, false if not
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return the number of elements in the queue
	 */
	int size();

	/**
	 * Sets the queue's capacity.
	 * 
	 * @param capacity capacity to set
	 * @throws IllegalArgumentException if negative or less than number of elements
	 *                                  in queue
	 */
	void setCapacity(int capacity);
}
