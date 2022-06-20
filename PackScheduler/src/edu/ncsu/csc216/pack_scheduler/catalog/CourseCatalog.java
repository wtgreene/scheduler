/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Holds the Course catalog. Contains methods for loading from a file, adding /
 * removing Courses from the catalog, and saving the catalog to a file.
 * 
 * @author Will Greene
 */
public class CourseCatalog {

	/** Course catalog */
	private SortedList<Course> catalog;

	/**
	 * Constructs an empty catalog
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs an empty catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param filename file to read Course records from
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public void loadCoursesFromFile(String filename) {
		try {
			catalog = CourseRecordIO.readCourseRecords(filename);
		} catch (FileNotFoundException e) { // Instructions were to create an IllegalAggumentException instead but that
											// did not work
			System.out.println("Cannot find file.");
		}
	}

	/**
	 * Adds a Course to the catalog. If the Course is already in the catalog,
	 * nothing is added.
	 * 
	 * @param name         Course name
	 * @param title        Course title
	 * @param section      Course section
	 * @param credits      Course's number of credit hours
	 * @param instructorId Course's instructor id
	 * @param meetingDays  meeting days for Course as series of char
	 * @param startTime    Course start time
	 * @param endTime      Course end time
	 * @return true if Course is added, false if already in catalog
	 * @throws IllegalArgumentException if Course object cannot be constructed
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			String meetingDays, int startTime, int endTime) {
		Course c = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);

		for (int i = 0; i < catalog.size(); i++) {
			if (name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				return false;
			}
		}

		catalog.add(c);
		return true;
	}

	/**
	 * Removes a Course from the catalog. If the Course does not exist, nothing is
	 * removed.
	 * 
	 * @param name    Course name
	 * @param section Course section
	 * @return true if Course was removed, false if not in the catalog
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				catalog.remove(i);
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns a Course from the catalog.
	 * 
	 * @param name    Course name
	 * @param section Course section
	 * @return the Course from the catalog, null if not found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				return catalog.get(i);
			}
		}

		return null;
	}

	/**
	 * Returns a 2D String array of the catalog.
	 * 
	 * @return 2D String array of the catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] s = new String[catalog.size()][3];

		if (catalog.size() == 0) {
			return s;
		}

		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			s[i] = c.getShortDisplayArray();
		}

		return s;
	}

	/**
	 * Saves Courses in the catalog to a file.
	 * 
	 * @param fileName name of file to save students to.
	 * @throws IllegalArgumentException if unable to write to file
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}
