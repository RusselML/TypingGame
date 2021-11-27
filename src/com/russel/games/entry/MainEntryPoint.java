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
	
	private StackPane root  = new StackPane();
	private long startTime;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		dictionary = new Dictionary(new GameManager());
		
		//setStage(primaryStage);
		
		
		Scene scene = new Scene(setUp());
        scene.setOnKeyPressed(e -> onKeyPress(e.getCode().toString()));

        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
	private Parent setUp() {
		//root = new StackPane();
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
//	private void setStage(Stage primaryStage) {	
//		//Create a button object to use in the program 
//		Button btn = new Button();
//		btn.setText("Get Random Word");
//		Label firstWord = new Label();
//		firstWord.setFont(new Font ("Ariel", 25));
//		
//		firstWord.setTranslateY(-50);
//		//firstWord.setText(arg0);
//		
//		VBox vbox = new VBox();
//		vbox.getChildren().addAll(btn, firstWord);
//		btn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				firstWord.setText(dictionary.getRandomWord());
//			}
//		});
//	
//		Scene scene = new Scene(vbox, 500, 650);
//		scene.setFill(Color.SKYBLUE);
//		
//		//root.getChildren().add(vbox);
//		
//		vbox.setAlignment(Pos.CENTER);
//		
//		primaryStage.setTitle("SpeedTyper!");
//		primaryStage.setScene(scene);	
//		primaryStage.show();
//	}
}
