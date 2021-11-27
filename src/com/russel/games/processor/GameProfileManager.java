package com.russel.games.processor;

import java.io.IOException;
import java.util.Set;

import com.russel.games.model.GameProfile;

/**
 * A game profile manager is responsible for the creation and manipulation of user profiles who play this game.
 * @author Russel Love
 *
 */
public class GameProfileManager implements ProfileManager {
	
	//Stores all of the profile objects created 
	private Set<GameProfile> profiles;
	
	@Override
	public void writeToProfile(String filePath) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createProfile(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openProfile(String userName) {
		// TODO Auto-generated method stub
		
	}

}
