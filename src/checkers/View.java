package checkers;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
		
		root.setRight(createRightPanel()); 
		root.setBottom(createBottomPanel());
        root.setCenter(createBoard());
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
		
		
	}

	private GridPane createBoard() {
		GridPane board = new GridPane();
	    int colour = 0; // 0 == White ,  1 == Black
		for (int column = 0; column < 8; column++) {
    		for (int row = 0; row < 8; row++) {
    			Rectangle tile = new Rectangle(80,80,80,80);
    			if (colour == 0) {
    				tile.setFill(Color.WHITE);
    				tile.setStroke(Color.web("#211f1f"));
    				colour = 1;
    				if(row == 7) { 
    				    colour = 0;
    				}
    				else {
    					colour = 1;
    				}
    			}
    			else {
    				tile.setFill(Color.BLACK);
    				tile.setStroke(Color.web("#211f1f"));
    				if (row == 7) {
    					colour = 1;
    				}
    				else {
    				    colour = 0;
    				}
    			}
    			board.add(tile, column, row);
    			
    		
    		}
		}
		
		return board;
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
