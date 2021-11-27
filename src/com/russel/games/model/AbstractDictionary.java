package com.russel.games.model;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.russel.games.processor.AFileReader;

/**
 * An abstract dictionary is an interface which is meant to be implemented in a way that allows a text file filled with words to be read and stored for later use.
 * @author Russel
 * @since 11/24/2021
 */
public abstract class AbstractDictionary {
	protected final AFileReader FILE_READER;
	protected Set<String> allWordsSet;
	protected String[] allWordsArray = null;
	
	/**
	 * Creates an abstract dictionary using words specifically found in the provided text file.
	 * If no text file was provided then the program will automatically use the default text file.
	 * @param aFileReader The TextFileReader used to read text from the specified location
	 * @throws IOException Throws I/O Exception if there is a problem reading from the file.
	 */
	protected AbstractDictionary(AFileReader aFileReader) throws IOException{
		
		//checks to see if another dictionary file was passed to the constructor
		Objects.requireNonNull(aFileReader, "AFileReader must not be null!");
	
		FILE_READER = aFileReader;
		
		//Retrieve set of words from the file our fileReader is attached to
		allWordsSet = FILE_READER.getAllLines();
	}
	
	/**
	 * Returns an array of all words contained in the text file
	 * @return an array of all words contained in the dictionary 
	 */
	protected String[] getAllWords(){
		
		//Convert current set to an array, then set the set to null for garbage collection
		if(Objects.isNull(allWordsArray)) {
			allWordsArray = allWordsSet.toArray(new String[0]);
			allWordsSet = null;
		}
		
		return allWordsArray;
	}
	
	/**
	 * Returns any random word from the dictionary
	 * @return any random word from the dictionary
	 */
	abstract String getRandomWord();
	
	/**
	 * Returns a random word from the dictionary of a specified length;
	 */
	//abstract String getLengthWord();
}
