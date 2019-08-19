package checkers;

import java.util.ArrayList;

public class Game {
	private CheckerBoard model;
	private CheckerMovementHandler checkerController;
	private String currentPlayer = "Black";
	private View view;
	private boolean moveAfterAJump;
	//private int[] positionOfCheckerThatJumed;
	
	public Game(CheckerBoard model, View view) {
		this.model = model;
		this.view = view;
		this.checkerController = new CheckerMovementHandler(this.model);
		this.checkerController.setGame(this);
		
	}

    public void moveLeft(Checker checker) {
    	if (checker.getColour() == this.currentPlayer) {
	        this.checkerController.move(checker, "left");
    	}
    	else {
    		MessageBox.createMessageBox("You Cannot Move Now", "It's Not Your Turn");
    	}
    }
    
    public void moveRight(Checker checker) {
    	if (checker.getColour() == this.currentPlayer) {
    	    this.checkerController.move(checker, "right");
    	}
    	else {
    		MessageBox.createMessageBox("You Cannot Move Now", "It's Not Your Turn");
    	}
    }

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public void changeCurrentPlayer () {
		if (this.currentPlayer == "Black" ) {
			this.setCurrentPlayer("White");
		}
		else {
			this.setCurrentPlayer("Black");
		}
		this.view.updateCurrentPlayerDisplay(this.currentPlayer);
	}

	public boolean isMoveAfterAJump() {
		return moveAfterAJump;
	}

	public void setMoveAfterAJump(boolean moveAfterAJump) {
		this.moveAfterAJump = moveAfterAJump;
	}
	
	/*public boolean isJumpsAvailable(String colour) {
		
		String diagonalCheckerColour;
		String[][] board = this.model.getBoard();
		ArrayList<int[]> checkerPositions;
		if (colour == "Black") {
			checkerPositions = this.model.getBlackCheckerPos();
			diagonalCheckerColour = "White";
			
		}
		else {
			checkerPositions = this.model.getWhiteCheckerPos();
			diagonalCheckerColour = "Black";
		}
		
		
		for (int i = 0; i < checkerPositions.size(); i++) {
			//if (this.checkerController.calcNewPosition(checkerPositions[i], "right"))
			
		}
		
		return false;
		
	}*/
	

}
