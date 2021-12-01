package com.russel.games.model;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import com.russel.games.processor.AFileReader;

/**
 * A dictionary is used to hold a large collection of words. These words may be accessed randomly at any time by the user. 
 * @author Russel
 *
 */
public class Dictionary extends AbstractDictionary {

	/**
	 * Constructs a dictionary of words using the text extracted from the filereader 
	 * @param aFileReader
	 * @throws IOException
	 */
	public Dictionary(AFileReader aFileReader) throws IOException {
		super(aFileReader);
	}

	@Override
	public String getRandomWord() {
		//Makes sure that the array of words has been initialized
		if(Objects.isNull(allWordsArray)) getAllWords();
		
		int high = allWordsArray.length-1;
		Random r = new Random();
		
		//gets a random word from the array
		return allWordsArray[r.nextInt(high)];
		
	}

}
