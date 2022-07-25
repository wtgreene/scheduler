package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file. CourseRecordIO.java used for reference.
 * 
 * @author Will Greene
 */
public class FacultyRecordIO {

	/**
	 * Reads Faculty records from a file and generates a list of valid Faculty. Any
	 * invalid Faculty are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Faculty records from
	 * @return a list of valid Faculty records
	 * @throws FileNotFoundException if the file cannot be found or read
	 * @throws IllegalArgumentException if fileReader cannot process Faculty
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < facultyList.size(); i++) {
					User current = facultyList.get(i);
					if (faculty.getId().equals(current.getId())) {
						duplicate = true;
						break;
					}
				}

				if (!duplicate) {
					facultyList.add(faculty);
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException();
			}
		}

		fileReader.close();

		return facultyList;
	}

	/**
	 * Reads a Faculty from a line of text.
	 * 
	 * @param line line of text to be read
	 * @return s Faculty object
	 * @throws IllegalArgumentException if invalid input file contents, no such
	 *                                  element exception or input mismatch
	 *                                  exception
	 */
	private static Faculty processFaculty(String line) {
		
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");

		try {
			
			String firstName = lineScanner.next();
			String lastName = lineScanner.next();
			String id = lineScanner.next();
			String email = lineScanner.next();
			String password = lineScanner.next();
			int maxCourses = lineScanner.nextInt();

			if (lineScanner.hasNext()) {
				lineScanner.close();
				throw new IllegalArgumentException("Invalid input file contents.");
			}

			lineScanner.close();
			return new Faculty(firstName, lastName, id, email, password, maxCourses);

		} catch (InputMismatchException e) {
			throw new IllegalArgumentException("No Such Element Exception.");
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Input Mismatch Exception.");
		}
	}

	/**
	 * Writes the given list of Faculty to a file.
	 * 
	 * @param fileName         file to write schedule of Faculty to
	 * @param facultyDirectory list of Faculty to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
			
			if (i != 0) {
				fileWriter.println();
			}
			
			fileWriter.print(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
	}
}
