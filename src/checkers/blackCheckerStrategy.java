package checkers;

public class blackCheckerStrategy {
	private CheckerBoard model;
	
	public blackCheckerStrategy(CheckerBoard board) {
		this.model = board;
	}

	public void moveLeft(Checker checker) {
		int[] currentPosition = checker.getPosition();
		int[] newPosition =  {currentPosition[0] - 1 , currentPosition[1] + 1}; 
		
		checker.setPosition(newPosition);
		this.model.updateBoard(checker, currentPosition);
		
	}
	
	
	

}
