package com.russel.games.entry;


import com.russel.games.processor.GameManager;
import com.russel.games.model.Dictionary;
import com.russel.games.processor.WordView;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainEntryPoint extends Application{
	Dictionary dictionary;
	
	private StackPane root;
	private long startTime;
	
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
	
    private void showNextWord() {
        String word = dictionary.getRandomWord();

        WordView view = new WordView(word);

        root.getChildren().setAll(view);

        startTime = System.nanoTime();
    }
    
    private void onKeyPress(String letter) {
        WordView view = (WordView) root.getChildren().get(0);
        view.handleKeyPress(letter);

        if (view.isFinished()) {
            long endTime = System.nanoTime() - startTime;

            System.out.printf("%.2f sec\n", endTime / 1000000000.0);

            showNextWord();
        }
    }

}
