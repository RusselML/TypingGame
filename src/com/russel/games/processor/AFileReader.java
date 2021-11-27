package com.russel.games.processor;

import java.io.IOException;
import java.util.Set;

public interface AFileReader {
	/**
	 * Reads all the lines of a text file into a single string which may be parsed for data 
	 * @param path location of the text file to be read
	 * @return A string of all lines contained in a file
	 * @throws IOException if any I/OExcepion occurs while trying to access file
	 */
	abstract String readText() throws IOException;
	
	/**
	 * 
	 * @param path location of the text file to be read
	 * @return A set of all words in a text file, all trailing white spaces removed.
	 * @throws IOException if any sort of I/OExceptiion occurs while trying to access file
	 */
	abstract Set<String> getAllLines() throws IOException;
	
	abstract void setFilePath(String filePath) throws IllegalArgumentException;
	
}
