package checkers;

public class Game {
	private CheckerBoard model;
	private CheckerMovementHandler checkerController;
	private String currentPlayer = "Black";
	
	public Game(CheckerBoard model) {
		this.model = model;
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
	}

}
