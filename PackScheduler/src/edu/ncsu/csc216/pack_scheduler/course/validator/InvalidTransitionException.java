/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Extends Exception class by providing a custom exception type.
 * 
 * @author Will Greene
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a InvalidTransitionException with a custom message.
	 * 
	 * @param message error message to be displayed
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Constructs an InvalidTransitionException with a default message.
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
