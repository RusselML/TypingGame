package com.russel.games.model;

import java.io.Serializable;

/**
 * A game profile is used to represent a user who plays the game
 * @author Russel
 *
 */
public class GameProfile implements Serializable{
	private String userName;
	private int wordsPerMinute;
	//TODO-find a data type which gets the current time, use it for storing data about when this profile was last used
	
	/**
	 * Creates a profile using the provided name 
	 * @param userName
	 */
	public GameProfile(String userName) {
		//TODO check if a profile with this username exists, if so add 1 to the end of the string (ex. if Russel exists, then make a new profile named Russel1)
		//alternativly force the user to type a new name?
		//setName(userName);
		this.userName = userName;
		this.wordsPerMinute = 0;
	}
	
	/**
	 * Used to verify a profile name before setting or changing it.
	 */
	private void setName(String fileName) {
		//TODO if this name is not null, delete the profile file that corresponds with this file name.
		//TODO if this is null then check if another profile exists with this name, if so add 1 the end of the string
		
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public int getWordsPerMinute() {
		return this.wordsPerMinute;
	}
	
	@Override
	public String toString() {
		return this.userName + "WPM: " + wordsPerMinute;
	}

}
