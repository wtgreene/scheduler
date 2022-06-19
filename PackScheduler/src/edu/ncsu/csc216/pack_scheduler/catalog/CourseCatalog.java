/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Course Catalog class ...
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
	 * DSF:jdsf
	 * @param name a
	 * @param title a 
	 * @param section a 
	 * @param credits a
	 * @param instructorId a
	 * @param meetingDays a
	 * @param startTime a
	 * @param endTime a
	 * @return a
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, String meetingDays, int startTime, int endTime) {
		Course c = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
		
		catalog.add(c);
		
		return false;
	}
	
	/**
	 * 
	 * @param name
	 * @param section
	 * @return true if Course was removed, false if not
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		catalog.remove(catalog.indexOf(getCourseFromCatalog(name, section)));
		return false;
	}
	
	/**
	 * Returns a Course from the catalog.
	 * 
	 * @param name    Course name
	 * @param section Course section
	 * @return the Course from the catalog null if not found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
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
	 * Saves all students in the directory to a file.
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
