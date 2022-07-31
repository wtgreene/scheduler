package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Sarah Heckman
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
//	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		SortedList<Course> courses = new SortedList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the ArrayList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the ArrayList with all the courses we read!
		return courses;
	}

	/**
	 * Reads a Course from a line of text.
	 * 
	 * @param line line of text to be read
	 * @return c Course object
	 * @throws IllegalArgumentException if invalid input file contents, no such
	 *                                  element exception or input mismatch
	 *                                  exception
	 */
	private static Course readCourse(String line) {
		
		Course c = null;
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");

		try {
			
			String name = lineScanner.next();
			String title = lineScanner.next();
			String section = lineScanner.next();
			int credits = lineScanner.nextInt();
			String instructorId = lineScanner.next();
			int enrollmentCap = lineScanner.nextInt();
			String meetingDays = lineScanner.next();

			if ("A".equals(meetingDays)) {
				if (lineScanner.hasNext()) {
					lineScanner.close();
					throw new IllegalArgumentException("Invalid input file contents.");
				}

				lineScanner.close();
				c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);
				
				Faculty f = null;
				
				try {
					
					f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);
					
					if (f != null) {
						f.getSchedule().addCourseToSchedule(c);
					}
					
				} catch (Exception e) {
					throw new IllegalArgumentException("Cannot attached faculty to course.");
				}
				
				return c;
			}

			else {
				int startTime = lineScanner.nextInt();
				int endTime = lineScanner.nextInt();

				if (lineScanner.hasNext()) {
					lineScanner.close();
					throw new IllegalArgumentException("Invalid input file contents.");
				}

				lineScanner.close();
				
				Faculty f = null;
				
				try {
					
					f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);
					
					if (f != null) {
						f.getSchedule().addCourseToSchedule(c);
					}
					
				} catch (Exception e) {
					throw new IllegalArgumentException("Cannot attached faculty to course.");
				}
				
				c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);
				return c;
			}

		} catch (InputMismatchException e) {
			throw new IllegalArgumentException("No Such Element Exception.");
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Input Mismatch Exception.");
		}
	}

	/**
	 * Writes the given list of Courses to a file.
	 * 
	 * @param fileName file to write schedule of Courses to
	 * @param courses  list of Courses to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
