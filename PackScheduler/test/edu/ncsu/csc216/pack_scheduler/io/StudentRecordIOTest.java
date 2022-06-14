package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

class StudentRecordIOTest {

	// validStudent variables renamed after using SortedList

	/** row 0 of student_records.txt */
	private String validStudent6 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** row 1 of student_records.txt */
	private String validStudent8 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** row 2 of student_records.txt */
	private String validStudent4 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** row 3 of student_records.txt */
	private String validStudent0 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** row 4 of student_records.txt */
	private String validStudent2 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** row 5 of student_records.txt */
	private String validStudent3 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** row 6 of student_records.txt */
	private String validStudent1 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** row 7 of student_records.txt */
	private String validStudent9 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** row 8 of student_records.txt */
	private String validStudent5 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** row 9 of student_records.txt */
	private String validStudent7 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	/** list form of student_records.txt */
	private String[] validStudents = { validStudent0, validStudent1, validStudent2, validStudent3, validStudent4,
			validStudent5, validStudent6, validStudent7, validStudent8, validStudent9 };

	/** encoded password */
	private String hashPW;
	/** encoded password algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
				Scanner actScanner = new Scanner(new FileInputStream(actFile));) {

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act);
			}

			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();

		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/**
	 * Encodes passwords for each Student.
	 * 
	 * @throws Exception if no such algorithm
	 */
	@BeforeEach
	void setUp() throws Exception {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = Base64.getEncoder().encodeToString(digest.digest());

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Tests StudentRecordIO.readStudentRecords().
	 * 
	 * @throws FileNotFoundException if file not found
	 */
	@Test
	void testReadStudentRecords() throws FileNotFoundException {
		String filepath = "test-files/student_records.txt";
		String filepath2 = "test-files/invalid_student_records.txt";

		SortedList<Student> students = StudentRecordIO.readStudentRecords(filepath);

		// testing same length
		assertEquals(students.size(), validStudents.length);

		// testing individual items
		for (int i = 0; i < validStudents.length; i++) {
			if (!validStudents[i].equals(students.get(i).toString())) {
				fail("Row " + i + " does not match");
			}
		}

		// testing invalid
		SortedList<Student> students2 = StudentRecordIO.readStudentRecords(filepath2);
		assertTrue(students2.isEmpty());
	}

	/**
	 * Tests StudentRecordIO.writeStudentRecords().
	 * 
	 * @throws IOException if can't write to file
	 */
	@Test
	void testWriteStudentRecords() throws IOException {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));

		Exception exception = assertThrows(IOException.class,
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", exception.getMessage());

		StudentRecordIO.writeStudentRecords("test-files/new.txt", students);
		checkFiles("test-files/expected_student_records.txt", "test-files/new.txt");
	}
}
