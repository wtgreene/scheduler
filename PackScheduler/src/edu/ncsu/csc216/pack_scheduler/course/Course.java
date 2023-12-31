/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Assembles information for a Course.
 * 
 * @author Will Greene
 */
public class Course extends Activity implements Comparable<Course> {

	/** Number of digits for a section */
	private static final int SECTION_LENGTH = 3;
	/** Minimum number of credits for a Course */
	private static final int MIN_CREDITS = 1;
	/** Maximum number of credits for a Course */
	private static final int MAX_CREDITS = 5;

	/** Course's name */
	private String name;
	/** Course's section */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;

	/** Course name validator */
	private CourseNameValidator validator = new CourseNameValidator();

	/** Course's roll */
	private CourseRoll roll;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name          name of Course
	 * @param title         title of Course
	 * @param section       section of Course
	 * @param credits       credit hours for Course
	 * @param instructorId  instructor's unity id
	 * @param enrollmentCap enrollment cap for the Course
	 * @param meetingDays   meeting days for Course as series of chars
	 * @param startTime     start time for Course
	 * @param endTime       end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);

		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name          name of Course
	 * @param title         title of Course
	 * @param section       section of Course
	 * @param credits       credit hours for Course
	 * @param instructorId  instructor's unity id
	 * @param enrollmentCap enrollment cap for the Course
	 * @param meetingDays   meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {

		// Throw exception if the name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		try {
			if (validator.isValid(name)) {
				this.name = name;
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. If the section is null, the section length is not
	 * 3 characters or if any of the section's 3 characters are not digits, an
	 * IllegalArgumentExcpetion is thrown.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {

		// Throw exception if section is null or not 3 characters
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		// Throw exception if any of section's 3 characters are not digits
		for (int i = 0; i < SECTION_LENGTH; i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Returns the Course's number of credit hours.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's number of credit hours. If the number of credit hours is
	 * "out of bounds," an IllegalArgumentException is thrown.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits parameter is invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Returns the instructor's unity id.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the instructor's unity id. If the instructorId is null or an empty
	 * string, an IllegalArgumentException is thrown.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId parameter is invalid
	 */
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	/**
	 * Sets the meeting days and time.
	 * 
	 * @param meetingDays the meetingDays to set
	 * @param startTime   the startTime to set
	 * @param endTime     the endTime to set
	 * @throws IllegalArgumentException if invalid meetingDays, startTime or endTime
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		}

		else {
			int numM = 0;
			int numT = 0;
			int numW = 0;
			int numH = 0;
			int numF = 0;
			int numA = 0;

			for (int i = 0; i < meetingDays.length(); i++) {
				switch (meetingDays.charAt(i)) {
				case 'M':
					numM++;
					break;
				case 'T':
					numT++;
					break;
				case 'W':
					numW++;
					break;
				case 'H':
					numH++;
					break;
				case 'F':
					numF++;
					break;
				case 'A':
					numA++;
					break;
				default:
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}

			if (numM > 1 || numT > 1 || numW > 1 || numH > 1 || numF > 1 || numA > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (numA == 1 && (numM == 1 || numT == 1 || numW == 1 || numH == 1 || numF == 1)) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {

		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ Integer.toString(roll.getEnrollmentCap()) + "," + getMeetingDays();
		}

		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + Integer.toString(roll.getEnrollmentCap()) + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Populates the rows of the course catalog and student schedule.
	 * 
	 * @return course catalog and student schedule
	 */
	@Override
	public String[] getShortDisplayArray() {

		String[] s = new String[5];

		s[0] = name;
		s[1] = section;
		s[2] = getTitle();
		s[3] = getMeetingString();
		s[4] = Integer.toString(roll.getOpenSeats());

		return s;
	}

	/**
	 * Displays the final schedule.
	 * 
	 * @return final schedule
	 */
	@Override
	public String[] getLongDisplayArray() {

		String[] s = new String[7];

		s[0] = name;
		s[1] = section;
		s[2] = getTitle();
		s[3] = Integer.toString(credits);
		s[4] = instructorId;
		s[5] = getMeetingString();
		s[6] = "";

		return s;
	}

	/**
	 * Returns when an activity is a duplicate of the current Course.
	 * 
	 * @param activity Activity object to compare to
	 * @return true if duplicate, false if not
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course c = (Course) activity;
			return name.equals(c.getName());
		}

		return false;
	}

	/**
	 * Compares the alphabetic order of 2 Courses.
	 */
	@Override
	public int compareTo(Course c) {
		if (name.compareTo(c.name) != 0) {
			return name.compareTo(c.name);
		}

		else {
			return section.compareTo(c.section);
		}
	}

	/**
	 * Compares the alphabetic order of 2 Activities.
	 */
	@Override
	public int compareTo(Activity a) {
		Course c = (Course) a;

		if (name.compareTo(c.name) != 0) {
			return name.compareTo(c.name);
		}

		else {
			return section.compareTo(c.section);
		}
	}

	/**
	 * Returns Course Roll.
	 * 
	 * @return course roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
}
