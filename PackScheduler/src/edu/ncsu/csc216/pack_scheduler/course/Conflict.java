/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Checks for a time conflicts.
 * 
 * @author Will Greene
 */
public interface Conflict {
	
	/**
	 * Checks for a time conflict between 2 Activities.
	 * 
	 * @param possibleConflictingActivity Activity to compare to
	 * @throws ConflictException if there is a time conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}