/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Custom LinkedList (recursive).
 * 
 * @author Will Greene
 * @param <E> element
 */
public class LinkedListRecursive<E> {

	/** front of list */
	private ListNode front;
	/** list size */
	private int size;

	/**
	 * Constructs a LinkedListRecursive object.
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * Returns whether the list is empty.
	 * 
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns list size.
	 * 
	 * @return list size
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the list.
	 * 
	 * @param element element to add
	 * @return whether element was added
	 * @throws IllegalArgumentException if duplicate
	 */
	public boolean add(E element) {

		if (isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return true;
		}

		if (front.contains(element)) { // can skip LinkedListRecursive.contains() since isEmpty() has been called
			throw new IllegalArgumentException();
		}

		return front.add(element);
	}

	/**
	 * Adds an element to the list at a specified position.
	 * 
	 * @param index   position in list
	 * @param element element to add
	 * @throws IllegalArgumentException  if duplicate
	 * @throws IndexOutOfBoundsException if invalid index
	 * @throws NullPointerException      if null element
	 */
	public void add(int index, E element) {

		if (element == null) {
			throw new NullPointerException();
		}

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (contains(element)) {
			throw new IllegalArgumentException();
		}

		if (index == 0) {
			front = new ListNode(element, front);
			size++;
		}

		else {
			front.add(index - 1, element);
		}
	}

	/**
	 * Returns an element at a specified position.
	 * 
	 * @param index position in list
	 * @return an element at a specified position
	 * @throws IndexOutOfBoundsException if invalid index
	 */
	public E get(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return front.get(index);
	}

	/**
	 * Removes an element from the list.
	 * 
	 * @param element element to remove
	 * @return whether the element was removed
	 * @throws NullPointerException if null element
	 */
	public boolean remove(E element) {

		if (element == null) {
			return false;
		}

		if (isEmpty()) {
			return false;
		}

		if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		}

		return front.remove(element);
	}

	/**
	 * Removes an element from the list at a specified position.
	 * 
	 * @param index position in list
	 * @return removed element
	 * @throws IndexOutOfBoundsException if invalid index
	 */
	public E remove(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			E rtn = front.data;
			front = front.next;
			size--;
			return rtn;
		}

		return front.remove(index);
	}

	/**
	 * Replaces an element with another at a specified position.
	 * 
	 * @param index   position in list
	 * @param element to replace
	 * @return replaced element
	 * @throws IndexOutOfBoundsException if invalid index
	 * @throws IllegalArgumentException if duplicate
	 */
	public E set(int index, E element) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (contains(element)) {
			throw new IllegalArgumentException();
		}

		return front.set(index, element);
	}

	/**
	 * Returns whether the list contains an element.
	 * 
	 * @param element element that list may contain
	 * @return true if contained, false if not
	 */
	public boolean contains(E element) {

		if (isEmpty()) {
			return false;
		}

		return front.contains(element);
	}

	/**
	 * Node used in LinkedListRecursive.
	 */
	private class ListNode {

		/** data in node */
		public E data;
		/** next node in list */
		public ListNode next;

		/**
		 * Adds an element to the list.
		 * 
		 * @param element element to add
		 * @return whether element was added
		 */
		public boolean add(E element) {

			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			}

			return next.add(element);
		}

		/**
		 * Adds an element to the list at a specified position.
		 * 
		 * @param index   position in list
		 * @param element element to add
		 */
		public void add(int index, E element) {

			if (index == 0) {
				next = new ListNode(element, next);
				size++;
			}

			else {
				next.add(index - 1, element);
			}
		}

		/**
		 * Returns an element at a specified position.
		 * 
		 * @param index position in list
		 * @return an element at a specified position
		 */
		public E get(int index) {

			if (index == 0) {
				return data;
			}

			return next.get(index - 1);
		}

		/**
		 * Removes an element at a specified position.
		 * 
		 * @param index position in list
		 * @return removed element
		 */
		public E remove(int index) {

			if (index == 1) {
				E rtn = next.data;
				next = next.next;
				size--;
				return rtn;
			}

			return next.remove(index - 1);
		}

		/**
		 * Removes an element from the list.
		 * 
		 * @param element element to remove
		 * @return whether the element was removed
		 */
		public boolean remove(E element) {

			if (next == null) {
				return false;
			}

			if (next.data.equals(element)) {
				next = next.next;
				size--;
				return true;
			}

			return next.remove(element);
		}

		/**
		 * Replaces an element with another at a specified position.
		 * 
		 * @param index   position in list
		 * @param element to replace
		 * @return replaced element
		 */
		public E set(int index, E element) {

			if (index == 0) {
				E rtn = data;
				data = element;
				return rtn;
			}

			return next.set(index - 1, element);
		}

		/**
		 * Returns whether the list contains an element.
		 * 
		 * @param element element that list may contain
		 * @return true if contained, false if not
		 */
		public boolean contains(E element) {

			if (data == element) {
				return true;
			}

			if (next != null) {
				return next.contains(element);
			}
			
			return false;
		}

		/**
		 * Constructs a ListNode object.
		 * 
		 * @param data data in node
		 * @param next next node in list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
