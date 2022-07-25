/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

/**
 * Custom LinkedAbstractSequentialList class.
 * 
 * @author Will Greene
 * @param <E> element
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	
	/** front of list */
	private ListNode front;
	/** back of list */
	private ListNode back;
	/** size of list */
	private int size;
	
	/**
	 * Constructs a LinkedList object.
	 */
	public LinkedList() {
		
		front = new ListNode(null);
		back = new ListNode(null);
		
		front.next = back;
		back.prev = front;
		
		size = 0;
	}
	
	/**
	 * Returns list size.
	 * @return list size
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * Adds an element at an index.
	 * 
	 * @param index index to add at
	 * @param element element to add
	 * @throws IllegalArgumentException if duplicate
	 */
	@Override
	public void add(int index, E element) {
		
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		super.add(index, element);
	}
	
	

	@Override
	public E set(int index, E element) {
		
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		return super.set(index, element);
	}

	/**
	 * Returns a positioned ListIterator.
	 * 
	 * @param index list position of iterator
	 * @return a positioned ListIterator
	 */
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}
	
	/**
	 * Nodes used for LinkedLists.
	 * 
	 * @author Will Greene
	 */
	private class ListNode {
		
		/** data in the node */
		public E data;
		/** next node in the list */
		public ListNode next;
		/** previous node in the list */
		public ListNode prev;
		
		/**
		 * Constructs a ListNode object - short version.
		 * 
		 * @param data data in the node
		 */
		public ListNode(E data) {
			this(data, null, null);
		}
		
		/**
		 * Construct a ListNode object - long version.
		 * 
		 * @param data data in the node
		 * @param prev previous node in the list
		 * @param next next node in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private class LinkedListIterator implements ListIterator<E> {
		
		/** ListNode returned on previous() call */
		private ListNode previous;
		/** ListNode returned on next() call */
		private ListNode next;
		/** index returned on previousIndex() call */
		public int previousIndex;
		/** index returned on nextIndex() call */
		public int nextIndex;
		/** ListNode returned on last previous() or next() call */
		private ListNode lastRetrieved;
		
		/**
		 * Constructs a LinkedListIterator object.
		 * 
		 * @param index list position of the iterator
		 */
		public LinkedListIterator(int index) {
			
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}

			previous = front;

			for (int i = 0; i < index; i++) {
				previous = previous.next;
			}

			next = previous.next;
			
			previousIndex = index - 1;
			nextIndex = index;
		}
		
		/**
		 * Returns whether the list contains an element next.
		 * @return whether the list contains an element next
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}
		
		/**
		 * Returns the next element in the list.
		 * @return the next element in the list
		 * @throws NoSuchElementException if no element to retrieve
		 */
		@Override
		public E next() {
			
			if (!hasNext()) {
				int i = 0;
				i = i + 1;
//				return NoSuchElementException();
			}
			
			lastRetrieved = next;
			
			previous = previous.next;
			next = next.next;
			
			previousIndex++;
			nextIndex++;
			
			return lastRetrieved.data;
		}
		
		/**
		 * Returns whether the list contains an element previous to it.
		 * @return whether the list contains an element previous to it
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}
		
		/**
		 * Returns the previous element in the list.
		 * @return the previous element in the list
		 * @throws NoSuchElementException if no element to retrieve
		 */
		@Override
		public E previous() {
			
			if (!hasPrevious()) {
				int i = 0;
				i = i + 1;
//				return NoSuchElementException();
			}
			
			lastRetrieved = previous;
			
			previous = previous.prev;
			next = next.prev;
			
			previousIndex--;
			nextIndex--;
			
			return lastRetrieved.data;
		}
		
		/**
		 * Returns index of next element.
		 * @return index of next element
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Returns index of previous element.
		 * @return index of previous element
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}
		
		/**
		 * Removes an element from the list last returned by next() or previous().
		 * 
		 * @throws IllegalStateException if lastRetrieved is null
		 */
		@Override
		public void remove() {
			
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			if (lastRetrieved.equals(previous)) {
				previous.prev.next = next;
				next.prev = previous.prev;
			}
			
			if (lastRetrieved.equals(next)) {
				next.next.prev = previous;
				previous.next = next.next;
			}
			
			lastRetrieved = null;
			
			size--;
		}

		/**
		 * Replaces the last element returned by next() or previous() with the specified element.
		 * 
		 * @param element element to replace
		 * @throws IllegalStateException if lastRetrieved is null
		 */
		@Override
		public void set(E element) {
			
			if (lastRetrieved.data == null) {
				throw new NullPointerException();
			}
			
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			lastRetrieved.data = element;
		}

		/**
		 * Adds an element before the element that would be returned by next().
		 * 
		 * @param element element to add
		 */
		@Override
		public void add(E element) {
			
			if (element == null) {
				throw new NullPointerException();
			}
			
			ListNode node = new ListNode(element, previous, next);
			
			previous.next = node;
			next.prev = node;
			
			previous = node;
			
			previousIndex++;
			nextIndex++;
			
			size++;
			
			lastRetrieved = null;
		}
	}
}
