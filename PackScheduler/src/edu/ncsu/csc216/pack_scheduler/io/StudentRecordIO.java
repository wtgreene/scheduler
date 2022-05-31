package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file. CourseRecordIO.java used for reference.
 * 
 * @author Will Greene
 *
 */
public class StudentRecordIO {

	/**
	 * Reads student records from a file and generates a list of valid Students. Any
	 * invalid Students are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Student records from
	 * @return a list of valid Student records
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Student> students = new ArrayList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < students.size(); i++) {
					Student current = students.get(i);
					if (student.getId().equals(current.getId())) {
						duplicate = true;
						break;
					}
				}

				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException e) {
				// leave blank?
			}
		}

		fileReader.close();

		return students;
	}

	/**
	 * Reads a Student from a line of text.
	 * 
	 * @param line line of text to be read
	 * @return s Student object
	 * @throws IllegalArgumentException if invalid input file contents, no such
	 *                                  element exception or input mismatch
	 *                                  exception
	 */
	private static Student processStudent(String line) {
		Student s = null;
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");

		try {
			String firstName = lineScanner.next();
			String lastName = lineScanner.next();
			String id = lineScanner.next();
			String email = lineScanner.next();
			String password = lineScanner.next();
			int maxCredits = lineScanner.nextInt();

			if (lineScanner.hasNext()) {
				lineScanner.close();
				throw new IllegalArgumentException("Invalid input file contents.");
			}

			lineScanner.close();
			s = new Student(firstName, lastName, id, email, password, maxCredits);
			return s;

		} catch (InputMismatchException e) {
			throw new IllegalArgumentException("No Such Element Exception.");
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Input Mismatch Exception.");
		}
	}

	/**
	 * Writes the given list of Students to a file.
	 * 
	 * @param fileName         file to write schedule of Students to
	 * @param studentDirectory list of Students to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
	}

}
