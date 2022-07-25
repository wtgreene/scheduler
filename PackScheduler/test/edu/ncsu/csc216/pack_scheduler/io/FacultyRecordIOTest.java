package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertThrows;
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

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests FacultyRecordIO.java
 * 
 * @author Will Greene
 */
class FacultyRecordIOTest {

	/** row 0 of Faculty_records.txt */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	/** row 1 of Faculty_records.txt */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** row 2 of Faculty_records.txt */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** row 3 of Faculty_records.txt */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** row 4 of Faculty_records.txt */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** row 5 of Faculty_records.txt */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** row 6 of Faculty_records.txt */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** row 7 of Faculty_records.txt */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";

	/** list form of Faculty_records.txt */
	private String[] validFacultys = { validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4,
			validFaculty5, validFaculty6, validFaculty7 };

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
	 * Encodes passwords for each Faculty.
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

			for (int i = 0; i < validFacultys.length; i++) {
				validFacultys[i] = validFacultys[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Tests FacultyRecordIO.readFacultyRecords().
	 * 
	 * @throws FileNotFoundException if file not found
	 */
	@Test
	void testReadFacultyRecords() throws FileNotFoundException {
		String filepath = "test-files/faculty_records.txt";

		LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(filepath);

		// testing same length
		assertEquals(8, faculty.size());

		// testing individual items
		for (int i = 0; i < validFacultys.length; i++) {
			if (!validFacultys[i].equals(faculty.get(i).toString())) {
				fail("Row " + i + " does not match");
			}
		}
		
		assertThrows(IllegalArgumentException.class, () -> FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt"));
	}

	/**
	 * Tests FacultyRecordIO.writeFacultyRecords().
	 * 
	 * @throws IOException if can't write to file
	 */
	@Test
	void testWriteFacultyRecords() throws IOException {
		LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
		
		assertEquals(8, faculty.size());

		FacultyRecordIO.writeFacultyRecords("test-files/faculty_records2.txt", faculty);
		checkFiles("test-files/expected_full_faculty_records.txt", "test-files/faculty_records2.txt");
	}
}
