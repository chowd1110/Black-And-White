package checkers;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
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
        root.setCenter(this.createBoard());
		
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("styles.css");
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
		AddCheckers(board);
		
		return board;
	}
	

	private void AddCheckers(GridPane board) {
		// the starting coordinates of the checkers
		int[][] blackCheckerPos = {{0,1},{1,0},{1,2},{2,1},{3,0},
				{3,2},{4,1},{5,0},{5,2},{6,1},{7,0},{7,2}};
		int[][] whiteCheckerPos = {{0,5},{0,7},{1,6},{2,5},{2,7},{3,6},{4,5},
				{4,7},{5,6},{6,5},{6,7},{7,6}};
		
		// initiate the checkers inside the board
		for (int i = 0; i < blackCheckerPos.length; i++) {
			
			int columnIndex = blackCheckerPos[i][0];
			int rowIndex = blackCheckerPos[i][1];
			Checker checker = new Checker(blackCheckerPos[i],"Black");
			checker.setTranslateX(15);
			checker.getStyleClass().add("black-checker");
			checker.setOnAction(e -> this.switchWindow());
			board.add(checker, columnIndex, rowIndex);
		}
		
        for (int i = 0; i < whiteCheckerPos.length; i++) {
			
			int columnIndex = whiteCheckerPos[i][0];
			int rowIndex = whiteCheckerPos[i][1];
			Checker checker = new Checker(whiteCheckerPos[i],"White");
			
			checker.setTranslateX(15); // to align the checkers to the center of the tiles
			checker.getStyleClass().add("white-checker");
			checker.setOnAction(e -> this.switchWindow());
			board.add(checker, columnIndex, rowIndex);
		}
              		
	}
	
	private void switchWindow() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		window.setTitle("Make Your Move");
		
		HBox box = new HBox();
		Button left = new Button("left");
		Button right = new Button("right");
		box.getChildren().addAll(left, right);
		box.setAlignment(Pos.CENTER);
		Scene scene = new Scene(box, 300, 300);
		
		window.setScene(scene);
		window.showAndWait();
		
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
