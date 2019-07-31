package checkers;

public class Game {
	private CheckerBoard model;
	private blackCheckerStrategy blackCheckerController;
	
	public Game(CheckerBoard model) {
		this.model = model;
		this.blackCheckerController = new blackCheckerStrategy(this.model);
	}

    public void moveLeft(Checker checker) {
	    this.blackCheckerController.moveLeft(checker);
    }
    
    public void moveRight(Checker checker) {
    	
    }

}
