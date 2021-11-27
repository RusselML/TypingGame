package com.russel.games.processor;

import java.util.BitSet;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 */
public class WordView extends HBox {

    private char[] letters;
    private BitSet charErrors;
    private int correctLetters = 0;
    
    //to keep track of all the errors made while typing
    private static int error = 0;

    public WordView(String word) {
        letters = word.toUpperCase().toCharArray();
        
        //initialize the bitset to be true
        charErrors = new BitSet(word.length());
        
        for(int i = 0; i < word.length(); i++) {
        	charErrors.set(i);
        }

        for (char c : letters) {
            Text letter = new Text(c + "");
            letter.setFont(Font.font(108));
            letter.applyCss();
            getChildren().add(letter);
        }

        setAlignment(Pos.CENTER);
    }

    public void handleKeyPress(String letter) {
        if (isFinished())
            return;

        char c = letters[correctLetters];
        
        //TODO modify this code so that instead of showing nothing, the letters are grayed out if they are typed correctly, 
        //red if typed incorrectly, and white if they still need to be typed
        if (letter.charAt(0) == c && charErrors.get(correctLetters)) {
            //TODO change this letter to gray
        	//getChildren().get(correctLetters).setVisible(false);
        	getChildren().get(correctLetters).setStyle("-fx-fill: gray;");
            correctLetters++;
        } else if (letter.charAt(0) != c && charErrors.get(correctLetters)){
            //first incorrectly typed - TODO change this letter to red
        	 charErrors.set(correctLetters, false);
        	 
        	error++;
        } else if(letter.charAt(0) == c){
        	//TODO?? 
        	getChildren().get(correctLetters).setStyle("-fx-fill: red;");
        	correctLetters++;
        }
    }

    public boolean isFinished() {
        return correctLetters == letters.length;
    }
}
