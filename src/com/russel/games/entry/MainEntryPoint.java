package com.russel.games.entry;

import java.util.Stack;

import com.russel.games.model.Dictionary;
import com.russel.games.processor.GameManager;
import com.russel.games.processor.WordView;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is a small application I put together with the intent of improving my touch-typing speed.
 * Right now this program simply displays random words on the screen, but eventually I would like 
 * to have this application display words and their definitions on the screen to type out. I can see
 * this application becoming a useful tool to not only increase children's typing speed, but also their 
 * vocabulary.
 * @author Russel Love russelmlove@gmail.com
 * @since 11/30/2021
 */
public class MainEntryPoint extends Application{
	Dictionary dictionary;
	
	private VBox root;
	private long startTime;
	private Stack<String> wordStack;
	private int index = 0;
	/**
	 * starting point of the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		dictionary = new Dictionary(new GameManager());
		
		//get the scene setup
		Scene scene = new Scene(setUp());
		
		//setup the initial words
		setInitialWords();
		
		showNextWord();
		
		//get the scene to listen for keyboard events 
        scene.setOnKeyPressed(e -> onKeyPress(e.getCode().toString()));

        primaryStage.setTitle("SpeedTyper!");
        
        
        //display the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.show();
       
	}
	
	/**
	 * sets up the word stack with initial values
	 */
	private void setInitialWords() {
		wordStack = new Stack<String>();
		for(int i = 0; i < 2; i++) wordStack.push(" ");
		for(int i = 0; i < 2; i++) wordStack.push((dictionary.getRandomWord()));
		for(int i = 0; i < wordStack.size(); i++) root.getChildren().add(new WordView(wordStack.get(i)));
		System.out.println(wordStack);
		//root.getChildren().add(new WordView(wordStack.get(0)));
		//root.getChildren().add(new WordView(wordStack.get(1)));
		
	}
	
	
	private Parent setUp() {
		//root = new StackPane();
		root = new VBox();
		root.setPrefSize(900, 700);		
		return root;
	}
	
	/**
	 * grabs a random word from the dictionary and prepares it to display it on the screen.
	 * TODO - grab word from the stack instead of getting a new word from the dictionary
	 */
    private void showNextWord() {
        //String word = dictionary.getRandomWord();
    	
    	//get the word at index3, then adjust the stack so the words move up
    	
    	String word = wordStack.get(3);
    	//wordStack.pop();
    	//wordStack.remove(0);
    	
    	//wordStack.push(dictionary.getRandomWord());
    	
        WordView view = new WordView(word);
        
        root.getChildren().add(view);
       // root.getChildren().setAll(view);


        startTime = System.nanoTime();
    }
    
    private void onKeyPress(String letter) {
        WordView view = (WordView) root.getChildren().get(2);
        view.handleKeyPress(letter);

        /*
         * TODO if word is finished, push the word up and make the text smaller. 
         * (try to keep the word as it was typed - meaning if they made an error make sure the word still shows that error)
         * each word the user types should get pushed up and the further it gets pushed, the smaller it should get until its off screen and it should get deleted
         */
        if (view.isFinished()) {
        	wordStack.remove(0);
        	
        	wordStack.push(dictionary.getRandomWord());
            long endTime = System.nanoTime() - startTime;

            System.out.printf("%.2f sec\n", endTime / 1000000000.0);
            //index++;
            root.getChildren().remove(0);
            root.getChildren().get(0).setScaleX(.5);
            root.getChildren().get(1).setScaleX(.75);
            root.getChildren().get(2).setScaleX(1);
            root.getChildren().get(3).setScaleX(.75);
           // root.getChildren().get(4).setScaleX(.5);
            
            root.getChildren().get(0).setScaleY(.5);
            root.getChildren().get(1).setScaleY(.75);    
            root.getChildren().get(2).setScaleY(1);
            root.getChildren().get(3).setScaleY(.75);
            
            System.out.println(wordStack);
           // root.getChildren().get(4).setScaleY(.5);
            showNextWord();
            root.getChildren().get(4).setScaleX(.5);
            root.getChildren().get(4).setScaleY(.5);
        }
    }

}
