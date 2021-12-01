package com.russel.games.entry;


import com.russel.games.processor.GameManager;
import com.russel.games.model.Dictionary;
import com.russel.games.processor.WordView;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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
	
	private StackPane root;
	private long startTime;
	
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
		
		//get the scene to listen for keyboard events 
        scene.setOnKeyPressed(e -> onKeyPress(e.getCode().toString()));

        primaryStage.setTitle("SpeedTyper!");
        
        //display the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
	private Parent setUp() {
		root = new StackPane();
		root.setPrefSize(900, 700);
		showNextWord();
		return root;
	}
	
	/**
	 * grabs a random word from the dictionary and prepares it to display it on the screen.
	 */
    private void showNextWord() {
        String word = dictionary.getRandomWord();

        WordView view = new WordView(word);

        root.getChildren().setAll(view);

        startTime = System.nanoTime();
    }
    
    private void onKeyPress(String letter) {
        WordView view = (WordView) root.getChildren().get(0);
        view.handleKeyPress(letter);

        /*
         * TODO if word is finished, push the word up and make the text smaller. 
         * (try to keep the word as it was typed - meaning if they made an error make sure the word still shows that error)
         * each word the user types should get pushed up and the further it gets pushed, the smaller it should get until its off screen and it should get deleted
         */
        if (view.isFinished()) {
            long endTime = System.nanoTime() - startTime;

            System.out.printf("%.2f sec\n", endTime / 1000000000.0);

            showNextWord();
        }
    }

}
