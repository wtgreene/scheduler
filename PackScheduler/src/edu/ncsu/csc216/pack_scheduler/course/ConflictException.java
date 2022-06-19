/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * TODO
 * @author Will Greene
 *
 */
public class ConflictException extends Exception {

	/** ID used for serializatio. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs TODO
	 * @param message m
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructs TODO
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
	
	

}
