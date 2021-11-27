package com.russel.games.model;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import com.russel.games.processor.AFileReader;

public class Dictionary extends AbstractDictionary {

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
