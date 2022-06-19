/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * TODO
 * @author Will Greene
 *
 */
public interface Conflict {
	
	/**
	 * TODO
	 * @param possibleConflictingActivity p
	 * @throws ConflictException c
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}