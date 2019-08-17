package checkers;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View extends Application implements Observer{
	private GridPane board;
	private TextField currentPlayerDisplay;
	private CheckerBoard model;
	private Game controller;
	private ArrayList <Checker> checkers = new ArrayList<Checker>();

	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.model = new CheckerBoard();
		this.model.addObserver(this);
		this.controller = new Game(this.model);
		
		
		primaryStage.setTitle("Black and White");
		
		
		BorderPane root =  new BorderPane();
		
		VBox rightPanel = new VBox();
		//rightPanel.setMarginTop(10);
	
		rightPanel.getChildren().add(createCurrentPlayerBox());
		
		root.setRight(rightPanel); 
		
		root.setBottom(createBottomPanel());
		this.board = this.createBoard();
        root.setCenter(board);
        
        
		
		
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
			this.checkers.add(checker);
			checker.setTranslateX(15);
			checker.getStyleClass().add("black-checker");
			checker.setOnAction(e -> this.switchWindow((Checker) e.getSource()));
			board.add(checker, columnIndex, rowIndex);
		}
		
        for (int i = 0; i < whiteCheckerPos.length; i++) {
			
			int columnIndex = whiteCheckerPos[i][0];
			int rowIndex = whiteCheckerPos[i][1];
			Checker checker = new Checker(whiteCheckerPos[i],"White");
			this.checkers.add(checker);
			checker.setTranslateX(15); // to align the checkers to the center of the tiles
			checker.getStyleClass().add("white-checker");
			checker.setOnAction(e -> this.switchWindow((Checker) e.getSource()));
			
			board.add(checker, columnIndex, rowIndex);
		}
              		
	}
	
	private void switchWindow(Checker checker) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		window.setTitle("Make Your Move");
		
		HBox box = new HBox();
		Button left = new Button("left");
		left.setOnAction(e -> {
			this.controller.moveLeft(checker);
			window.close();
			
		});
		
		
		Button right = new Button("right");
		right.setOnAction(e -> {
			this.controller.moveRight(checker);
			window.close();
		});
		box.getChildren().addAll(left, right);
		box.setAlignment(Pos.CENTER);
		Scene scene = new Scene(box, 300, 300);
		
		window.setScene(scene);
		window.showAndWait();
		window.close();
	}
	

	private HBox createBottomPanel() {
		
		return null;
	}

	private HBox createCurrentPlayerBox() {
		HBox box =  new HBox(5);
		
		Label label = new Label("Current Player :");
		label.setTranslateY(10);
		label.setFont(Font.font("Cambria", 20));
		
		TextField text = new TextField("Player 1 (Black)");
		text.setTranslateY(8);
		text.setDisable(true);
		text.setStyle("-fx-opacity: 1;");
		text.setFont(Font.font(15));
		this.setCurrentPlayerDisplay(text);
		box.getChildren().addAll(label, text);
		
		return box;
	
	}

	@Override
	public void update(Observable o, Object checker) {
		
		Checker checkerToModify = (Checker) checker;
		this.board.getChildren().remove(checkerToModify); // remove checker from its current position on the board
		int[] positionOfCheckerToModify = checkerToModify.getPosition();
		
		if (checkerToModify.hasJumped()) {
			this.removeJumpedChecker(checkerToModify.getJumpedCheckerPosition());
		}
		
		this.board.add((Node)checker,positionOfCheckerToModify[0], positionOfCheckerToModify[1]); // add checker to new position	
		
	}

	private void removeJumpedChecker(int[] position) {
		int column = position[0];
		int row = position[1];
		
		for (Checker checker: this.checkers) {
			 if(checker.getPosition()[0] == column && checker.getPosition()[1] == row) {
				 this.board.getChildren().remove(checker);			
			 }
		}		
	}

	public void setCurrentPlayerDisplay(TextField currentPlayerDisplay) {
		this.currentPlayerDisplay = currentPlayerDisplay;
	}
	
}
