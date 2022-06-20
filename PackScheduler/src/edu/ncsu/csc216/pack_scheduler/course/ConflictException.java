/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Extends Exception class by providing a custom excecption type.
 * 
 * @author Will Greene
 */
public class ConflictException extends Exception {

	/** ID used for serializatio. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a ConflictException with a custom message.
	 * 
	 * @param message error message to be displayed
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a ConflictException with a default message.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
