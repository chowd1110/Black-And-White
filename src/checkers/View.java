package checkers;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View extends Application implements Observer{
	private GridPane board;
	private CheckerBoard model;
	private Game controller;

	public static void main(String[] args) {
		launch(args);

	}
	

	@Override
	public void update(Observable o, Object arg) {
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
	}

}
