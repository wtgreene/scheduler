/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

// COMMENTS
// 1. line 15 (at or near) do i need an @param tag for the class? does it go above or below @author tag?
// 2. line 89 (at or near) comment

/**
 * Custom ArrayList class.
 * 
 * @param <E> Element
 * @author Will Greene
 */
public class ArrayList<E> extends AbstractList<E> {
	
	/** initial list size */
	private static final int INIT_SIZE = 10;
	
	/** list array */
	private E[] list;
	/** list size */
	private int size;
	
	/**
	 * ArrayList constructor.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[])new Object[INIT_SIZE];
		size = 0;
	}
	
	/**
	 * Adds an Element to the list.
	 * 
	 * @param index index to place Element at
	 * @param element Element to add
	 * @throws NullPointerException if null Element
	 * @throws IllegalArgumentException if duplicate Element
	 * @throws IndexOutOfBoundsException if out of range index
	 */
	@Override
	public void add(int index, E element) {
		
		// parameter error checking - null element
		if (element == null) {
			throw new NullPointerException();
		}
		
		// parameter error checking - duplicate element
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		
		// parameter error checking - out of range index
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if (size == list.length) {
			growArray();
		}
		
		if (list[index] == null && index == size) {
			list[index] = element;
		}
		
		else if (list[index] != null && index < size) {
			
			for (int i = size; i > index; i--) {
				list[i] = list[i - 1];
			}
			
			list[index] = element;
		}
		
		size++;
	}
	
	/**
	 * Doubles list capacity.
	 */
	@SuppressWarnings("unchecked") // can I use this?
	private void growArray() {
		
		E[] tempList = (E[])new Object[size];
		
		for (int i = 0; i < size; i++) {
			tempList[i] = list[i];
		}
		
		list = (E[])new Object[size * 2];
		
		for (int i = 0; i < size; i++) {
			list[i] = tempList[i];
		}
	}
	
	/**
	 * Removes an Element from the list.
	 * 
	 * @param index index of Element to remove
	 * @return removed Element
	 * @throws IndexOutOfBoundsException if out of range index
	 */
	@Override
	public E remove(int index) {
		
		// parameter error checking - out of range index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E tempE;
		
		if (index == size - 1) {
			tempE = list[index];
			list[index] = null;
		}
		
		else {
			
			tempE = list[index];
			
			for (int i = index; i < size; i++) {
				list[index] = list[index + 1];
			}
			
			list[size - 1] = null;
		}
		
		size--;
		
		return tempE;
	}
	
	/**
	 * Replaces an Element at a given index with a new Element.
	 * 
	 * @param index index of Element to replace
	 * @param element new Element
	 * @return replaced (old) Element
	 * @throws NullPointerException if null Element
	 * @throws IllegalArgumentException if duplicate Element
	 * @throws IndexOutOfBoundsException if out of range index
	 */
	@Override
	public E set(int index, E element) {
		
		// parameter error checking - null element
		if (element == null) {
			throw new NullPointerException();
		}
		
		// parameter error checking - duplicate element
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		
		// parameter error checking - out of range index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E tempE = list[index];
		list[index] = element;
		
		return tempE;
	}
	
	/**
	 * Returns an Element at a given index.
	 * 
	 * @param index index of Element
	 * @throws IndexOutOfBoundsException if out of range index
	 */
	@Override
	public E get(int index) {
		
		// parameter error checking - out of range index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		return list[index];
	}
	
	/**
	 * Returns size.
	 * @return size
	 */
	public int size() {
		return size;
	}
}
