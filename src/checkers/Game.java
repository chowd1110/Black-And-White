package checkers;

public class Game {
	private CheckerBoard model;
	private blackCheckerStrategy blackCheckerController;
	
	public Game(CheckerBoard model) {
		this.model = model;
		this.blackCheckerController = new blackCheckerStrategy(this.model);
	}

    public void moveLeft(Checker checker) {
	    this.blackCheckerController.move(checker,"left");
    }
    
    public void moveRight(Checker checker) {
    	this.blackCheckerController.move(checker, "right");
    }

}
