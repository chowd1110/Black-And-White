package checkers;

public class Game {
	private CheckerBoard model;
	private CheckerMovementHandler checkerController;
	private String currentPlayer = "Black";
	
	public Game(CheckerBoard model) {
		this.model = model;
		this.checkerController = new CheckerMovementHandler(this.model);
	}

    public void moveLeft(Checker checker) {
	    this.checkerController.move(checker, "left");
    }
    
    public void moveRight(Checker checker) {
    	this.checkerController.move(checker, "right");
    }

}
