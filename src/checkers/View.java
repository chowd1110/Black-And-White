package checkers;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application implements Observer{
	private GridPane board;
	private CheckerBoard model;
	private Game controller;

	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Black and White");
		BorderPane root =  new BorderPane();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		root.setRight(createRightPanel()); 
		root.setBottom(createBottomPanel());

		root.setCenter(createBoard());
		
		
		
		
		
		
	}

	private GridPane createBoard() {
		
		return null;
	}

	private HBox createBottomPanel() {
		
		return null;
	}

	private VBox createRightPanel() {
		return null;
	
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}

}
