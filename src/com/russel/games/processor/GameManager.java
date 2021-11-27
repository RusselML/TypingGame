package com.russel.games.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The game manager is responsible for setting up the game everytime the user starts this application.
 * @author Russel
 *
 */
public class GameManager implements AFileReader{
	private String filePath;
	private final GameProfileManager PROFILE_MANAGER;

	public GameManager() {
		this("DefaultDictionary.txt");
	}
	public GameManager(String filePath) {
		setFilePath(filePath);
		this.PROFILE_MANAGER = new GameProfileManager();
	}

	/**
	 * Reads all lines of text from a file and appends it to a single string
	 */
	@Override
	public String readText() throws IOException {

		
		StringBuilder allLines = new StringBuilder();
		setFilePath(filePath);

		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String t;
			while((t = br.readLine()) != null) {
				if(!t.isBlank())
					allLines.append(t).append("\n");
			}
		}catch (IOException e) {
			throw e;
		}

		return allLines.toString();


	}

	/**
	 * Splits a given string by newline charcters and returns the result as a set of words
	 */
	@Override
	public Set<String> getAllLines() throws IOException {
		HashSet<String> mySet = null;
		setFilePath(filePath);
		try {
			mySet = new HashSet<String>(Arrays.asList(readText().split("\n")));
		}catch(IOException e) {
			throw e;
		}
		
		return mySet;
	}
	
	/**
	 * Checks to see if a given file exists, if not then it will check and see if the default dictionary file exists before downloading it.
	 */
	//@Override
	 public void setFilePath(String filePath) throws IllegalArgumentException {
		File file = new File(filePath);
		if(Objects.isNull(filePath) || filePath.isBlank() || filePath.isEmpty()) {
			//throw new IllegalArgumentException();
			setFilePath("default");
		}else if(file.exists()){
			this.filePath = filePath;
		} else {
			this.filePath = "DefaultDictionary.txt";
			System.out.println("Couldn't find file with that name, switching to \"DefaultDictionary.txt\"");
			getDefaultDictionary(file);
		}
	}
	
	/**
	 * Verifys that the default dictionary exists locally when an invalid dictionary has been used,
	 * if the default dictionary does not exist then the file will be downloaded directly from its source.
	 * @param file
	 */
	private void getDefaultDictionary(File file) {
		String url = "http://www.mieliestronk.com/corncob_lowercase.txt";
		file = new File(this.filePath);
		if(!file.exists()) {
			Downloader down = new Downloader(url, file);
			down.run();
		}
	}

}
