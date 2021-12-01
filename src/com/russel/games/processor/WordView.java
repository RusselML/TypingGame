package com.russel.games.processor;

import java.util.BitSet;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * WordView is an extension of javafx.scene.text.HBox which adds some additional functionality 
 */
public class WordView extends HBox {

	private char[] letters;
	private int correctLetters = 0;

	//A bitset will be used to keep track of any errors made while typing
	private BitSet charErrors;
	private static int error = 0;

	/**
	 * Constructor - takes a word and converts it to actual uppercase text which will be displayed on the screen
	 * @param word
	 */
	public WordView(String word) {
		letters = word.toUpperCase().toCharArray();

		//initialize the bitset - set every index to be true 
		charErrors = new BitSet(word.length());
		for(int i = 0; i < word.length(); i++) {
			charErrors.set(i);
		}

		//Add each character as visible text to the hbox
		for (char c : letters) {
			Text letter = new Text(c + "");
			letter.setFont(Font.font(108));
			letter.applyCss();
			getChildren().add(letter);
		}

		setAlignment(Pos.CENTER);
	}

	/**
	 * Called whenever the stage detects an input from the keyboard has been entered.
	 * First the input is checked against the expected input at this position, then the expected input is updated 
	 * if the input is correct. Otherwise the current position is marked incorrect and the expected input isn't changed.
	 * @param letter the input entered by the user from the keyboard
	 */
	public void handleKeyPress(String letter) {
		//Verify that the word isn't finished yet
		if (isFinished())
			return;

		//grabs the expected letter 
		char c = letters[correctLetters];

		//checks to see if the user typed in the correct letter AND hasnt made any mistakes for this position
		if (letter.charAt(0) == c && charErrors.get(correctLetters)) {
			getChildren().get(correctLetters).setStyle("-fx-fill: gray;");
			correctLetters++;
		} else if (letter.charAt(0) != c && charErrors.get(correctLetters)){
			//first incorrectly typed - TODO change this letter to red
			charErrors.set(correctLetters, false);
			error++;
		} else if(letter.charAt(0) == c){
			//after the user incorrectly types a letter once, the color of the letter shall be red once they correctly type it
			getChildren().get(correctLetters).setStyle("-fx-fill: red;");
			correctLetters++;
		}
	}

	/**
	 * Returns true if all of the correct letters have been typed for the current word
	 * @return true if all of the correct letters have been typed for the current word
	 */
	public boolean isFinished() {
		return correctLetters == letters.length;
	}
}
