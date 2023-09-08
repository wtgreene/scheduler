package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects; 

/**
 * Assembles information for an Activity.
 * 
 * @author Will Greene
 */
public abstract class Activity implements Conflict {

	/** Maximum number of hours in military time */
	private static final int UPPER_HOUR = 24;
	/** Maximum number of minutes in military time */
	private static final int UPPER_MINUTE = 60;
	/** Course's title */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructs an Activity object with values for all fields.
	 * 
	 * @param title       title of Activity
	 * @param meetingDays meeting days for Activity as series of chars
	 * @param startTime   start time for Activity
	 * @param endTime     end time for Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course title. If the title is null or an empty string, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setTitle(String title) {

		// Throw exception if title is null or an empty string
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * Sets meetingDays, startTime and endTime for a Course. An
	 * IllegalArgumentException is thrown if meeting days is null, empty, or
	 * contains invalid characters, if an arranged class has non-zero start or end
	 * times, if start time is an incorrect time, if end time is an incorrect time
	 * or if end time is less than start time.
	 * 
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime   start time for Course
	 * @param endTime     end time for Course
	 * @throws IllegalArgumentException if meetingDays, startTime or endTime is
	 *                                  invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}

		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;

		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the Course's meeting days (days of week) as a series of chars.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Populates the rows of the course catalog and student schedule.
	 * 
	 * @return course catalog and student schedule
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Displays the final schedule.
	 * 
	 * @return final schedule
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks if a Course or Event is a duplicate.
	 * 
	 * @param activity activity
	 * @return true if duplicate, false if not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Returns the Course's meeting time in the form of a String.
	 * 
	 * @return Course meeting time
	 */
	public String getMeetingString() {

		if ("A".equals(meetingDays)) {
			return "Arranged";
		}

		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;

		String newStartTime = "";
		String newEndTime = "";

		if (startHour == 0) {
			if (startMin < 10) {
				newStartTime = UPPER_HOUR / 2 + ":0" + startMin + "AM";
			} else {
				newStartTime = UPPER_HOUR / 2 + ":" + startMin + "AM";
			}
		}

		else if (startHour < UPPER_HOUR / 2) {
			if (startMin < 10) {
				newStartTime = startHour + ":0" + startMin + "AM";
			} else {
				newStartTime = startHour + ":" + startMin + "AM";
			}
		}

		else if (startHour == UPPER_HOUR / 2) {
			if (startMin < 10) {
				newStartTime = startHour + ":0" + startMin + "PM";
			} else {
				newStartTime = startHour + ":" + startMin + "PM";
			}
		}

		else {
			if (startMin < 10) {
				newStartTime = (startHour - UPPER_HOUR / 2) + ":0" + startMin + "PM";
			} else {
				newStartTime = (startHour - UPPER_HOUR / 2) + ":" + startMin + "PM";
			}
		}

		if (endHour == 0) {
			if (endMin < 10) {
				newEndTime = UPPER_HOUR / 2 + ":0" + endMin + "AM";
			} else {
				newEndTime = UPPER_HOUR / 2 + ":" + endMin + "AM";
			}
		}

		else if (endHour < UPPER_HOUR / 2) {
			if (endMin < 10) {
				newEndTime = endHour + ":0" + endMin + "AM";
			} else {
				newEndTime = endHour + ":" + endMin + "AM";
			}
		}

		else if (endHour == UPPER_HOUR / 2) {
			if (endMin < 10) {
				newEndTime = endHour + ":0" + endMin + "PM";
			} else {
				newEndTime = endHour + ":" + endMin + "PM";
			}
		}

		else {
			if (endMin < 10) {
				newEndTime = (endHour - UPPER_HOUR / 2) + ":0" + endMin + "PM";
			} else {
				newEndTime = (endHour - UPPER_HOUR / 2) + ":" + endMin + "PM";
			}
		}

		return meetingDays + " " + newStartTime + "-" + newEndTime;
	}

	/**
	 * Generates a hashCode for Activity using all endTime, meetingDays, startTime
	 * and endTime.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(endTime, meetingDays, startTime, title);
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return endTime == other.endTime && Objects.equals(meetingDays, other.meetingDays)
				&& startTime == other.startTime && Objects.equals(title, other.title);
	}
	
	// Why does a Conflict interface exist (which is overridden below) instead of having this method only?
	// Is there a predefined checkConflict() method in a package used in this class?

	/**
	 * Checks for a time conflict.
	 * 
	 * @param possibleConflictingActivity p
	 * @throws ConflictException if there is a time overlap between the 2 Activities
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		for (int i = 0; i < meetingDays.length(); i++) {
			for (int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {

				// if the day is a match
				if (meetingDays.charAt(i) != 'A' && possibleConflictingActivity.getMeetingDays().charAt(j) != 'A'
						&& meetingDays.charAt(i) == possibleConflictingActivity.getMeetingDays().charAt(j)

						// and if any of the below 4 scenarios are true

						// 1. start time is inside of possibleConflictingActivity range
						&& (startTime >= possibleConflictingActivity.getStartTime()
								&& startTime <= possibleConflictingActivity.getEndTime()

								// 2. end time is inside of possibleConflictingActivity range
								|| endTime >= possibleConflictingActivity.getStartTime()
										&& endTime <= possibleConflictingActivity.getEndTime()

								// 3. possibleConflictingActivity start time is inside of range
								|| possibleConflictingActivity.getStartTime() >= startTime
										&& possibleConflictingActivity.getStartTime() <= endTime

								// 4. possibleConflictingActivity end time is inside of range
								|| possibleConflictingActivity.getEndTime() >= startTime
										&& possibleConflictingActivity.getEndTime() <= endTime)) {

					throw new ConflictException("Schedule conflict.");
				}
			}
		}
	}

	/**
	 * Compares 2 Activities for alphabetic order.
	 * 
	 * @param a Activity to compare to
	 * @return a negative or positive number, based on the alphabetic order of the Activities
	 */
	public abstract int compareTo(Activity a);
}