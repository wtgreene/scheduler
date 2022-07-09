package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom LinkedAbstractList class.
 * 
 * @author Will Greene
 * @param <E> Element
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	
	/** front item of list */
	private ListNode front;
	/** size of list */
	private int size;
	/** capacity of list */
	private int capacity;
	
	/**
	 * Constructs a LinkedAbstractList object.
	 * 
	 * @param capacity capacity of list
	 * @throws IllegalArgumentException if less than 0 or list size
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		size = 0;
		setCapacity(capacity);
	}
	
	/**
	 * Sets capacity of list.
	 * @param capacity capacity of list
	 */
	public void setCapacity(int capacity) {
		
		// parameter error checking - less than 0
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		
		// parameter error checking - less than 0
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = capacity;
	}
	
	/**
	 * Returns
	 */
	@Override
	public E get(int index) {
		
		// parameter error checking - out of range index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode current = front;
		
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current.data;
	}

	/**
	 * Sets 
	 */
	@Override
	public E set(int index, E element) {
		
		// parameter error checking - null element
		if (element == null) {
			throw new NullPointerException();
		}
		
		// parameter error checking - duplicate element
		ListNode current1 = front;
		while (current1.next != null) {
			if (current1.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			
			current1 = current1.next;
		}
		
		// parameter error checking - out of range index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E rtn = null;
		
		if (index == 0) {
			rtn = front.data;
			front.data = element;
//			front = new ListNode(element, front.next);
		}
		
		else {
			
			ListNode current2 = front;
			
			for (int i = 0; i < index - 1; i++) {
				current2 = current2.next;
			}
			
			rtn = current2.next.data;
			current2.next.data = element;
		}
		
		return rtn;
	}

	/**
	 * Adds an Element to the list.
	 * 
	 * @param index index to add Element at
	 * @param element Element to be added
	 * @throws IllegalArgumentException if size is equal to capacity or if Element is duplicate
	 * @throws NullPointerException if element to be added is null
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	@Override
	public void add(int index, E element) {
		
		// parameter error checking - size equal to capacity
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		
		// parameter error checking - null element
		if (element == null) {
			throw new NullPointerException();
		}
		
		// parameter error checking - duplicate element
		
		if (size > 1) {
			
			ListNode current1 = front;

			while (current1.next != null) {
				
				if (current1.data.equals(element)) {
					throw new IllegalArgumentException();
				}

				current1 = current1.next;
			}
		}
		
		else if (size == 1) {
			
			if (front.data.equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		
		// parameter error checking - index out of range
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == 0) {
			
			if (size == 0) {
				front = new ListNode(element);
			}
			
			else {
				front = new ListNode(element, front);
			}
		}
		
		else {
			
			ListNode current2 = front;

			for (int i = 0; i < index - 1; i++) {
				current2 = current2.next;
			}
			
			current2.next = new ListNode(element, current2.next);
		}
		
		size++;
	}
	
	/**
	 * Removes an Element from the list.
	 * 
	 * @param index index at which Element will be removed
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	@Override
	public E remove(int index) {
		
		// parameter error checking - index out of range
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E rtn = null;
		
		if (index == 0) {
			rtn = front.data;
			front = front.next;
		}
		
		else {

			ListNode current = front;

			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			
			rtn = current.next.data;
			current.next = current.next.next;
		}
		
		size--;
		return rtn;
	}
	
	/**
	 * Returns size of list.
	 * @return size of list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Nodes used for LinkedLists.
	 * 
	 * @author Will Greene
	 */
	private class ListNode {
		
		/** data in the node */
		E data;
		/** next node in the list */
		ListNode next;
		
		/**
		 * Constructs a ListNode object - without a next variable.
		 * 
		 * @param data data of current node
		 */
		public ListNode(E data) {
			this(data, null);
		}
		
		/**
		 * Constructs a ListNode object.
		 * 
		 * @param data data of current node
		 * @param next reference to next node
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
