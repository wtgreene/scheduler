package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of all Faculty enrolled at NC State. All Faculty have
 * a unique id.
 * 
 * @author Sarah Heckman
 * @author Will Greene
 */
public class FacultyDirectory {

	/** List of Faculty in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Creates an empty Faculty directory.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Creates an empty Faculty directory. All Faculty in the previous list are
	 * list unless saved by the user.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * Constructs the Faculty directory by reading in Faculty information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of Faculty
	 * @throws IllegalArgumentException if unable to read file
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Faculty to the directory. Returns true if the Faculty is added and
	 * false if the Faculty is unable to be added because their id matches another
	 * Faculty's id.
	 * 
	 * This method also hashes the Faculty's password for internal storage.
	 * 
	 * @param firstName      Faculty's first name
	 * @param lastName       Faculty's last name
	 * @param id             Faculty's id
	 * @param email          Faculty's email
	 * @param password       Faculty's password
	 * @param repeatPassword Faculty's repeated password
	 * @param maxCredits     Faculty's max credits.
	 * @return true if added
	 * @throws IllegalArgumentException if invalid password or passwords do not match
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCredits) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}

		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);

		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}

		// If an IllegalArgumentException is thrown, it's passed up from Faculty
		// to the GUI				
		Faculty f = new Faculty(firstName, lastName, id, email, hashPW, maxCredits);

		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(f.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(f);
	}

	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in
	 * base64 encoding. This allows the encoded digest to be safely copied, as it
	 * only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException if password cannot be hashed
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Removes the Faculty with the given id from the list of Faculty with the
	 * given id. Returns true if the Faculty is removed and false if the Faculty is
	 * not in the list.
	 * 
	 * @param facultyId Faculty's id
	 * @return true if removed
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns all Faculty in the directory with a column for first name, last
	 * name, and id.
	 * 
	 * @return String array containing Faculty first name, last name, and id.
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}

	/**
	 * Saves all Faculty in the directory to a file.
	 * 
	 * @param fileName name of file to save Faculty to.
	 * @throws IllegalArgumentException if unable to write to file
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	/**
	 * Returns a specified Faculty.
	 * 
	 * @param id Faculty's unity id
	 * @return a specified Faculty
	 * @throws IllegalArgumentException if not found in the directory
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(id)) {
				return facultyDirectory.get(i);
			}
		}
		
//		throw new IllegalArgumentException("User doesn't exist.");
		return null;
	}
}
