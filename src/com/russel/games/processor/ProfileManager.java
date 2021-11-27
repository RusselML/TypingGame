package com.russel.games.processor;

import java.io.IOException;

/**
 * @author Russel Love
 *
 */
public interface ProfileManager {
	
	/**
	 * Saves current profile data to a file using serialization so it may be accessed at a later time.
	 * @param filePath
	 * @throws IOException Thrown if there is a problem writing data to the file
	 */
	public void writeToProfile(String filePath) throws IOException;
	
	/**
	 * Creates a profile using the provided name
	 * @param userName 
	 */
	public void createProfile(String userName);
	
	/**
	 * Opens the profile with the specified name. If no profile exists under that name then the user should be asked if they would like to create a user instead.
	 * @param userName
	 * @throws IOException Thrown if there is a problem opening the user file
	 */
	public void openProfile(String userName);
}
