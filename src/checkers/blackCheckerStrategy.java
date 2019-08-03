package checkers;

public class blackCheckerStrategy {
	private CheckerBoard model;
	
	public blackCheckerStrategy(CheckerBoard board) {
		this.model = board;
	}

	public void moveLeft(Checker checker) {
		
		int[] currentPosition = checker.getPosition();
		int[] newPosition =  {currentPosition[0] - 1 , currentPosition[1] + 1}; 
		// check if newPosition is inside the board here
		String checkerAtNewPosition = this.model.getCheckerAtPosition(newPosition);
		
		
		if(checkerAtNewPosition == checker.getColour()) { // there is already a black checker at newPosition
			// window that says you cannot do that
		}
		
		else {
			
			if (checkerAtNewPosition == "Empty") { // there is neither a white or black checker at newPosition
				checker.setPosition(newPosition);
				this.model.updateBoard(checker, currentPosition);
			}
			else { // there is a white checker at newPosition. Make a Jump
				int[] checkerPositionAfterJumping = {newPosition[0] - 1 , newPosition[1] + 1};
				checker.setPosition(checkerPositionAfterJumping);
				checker.setJumped(true);
				this.model.updateBoard(checker, currentPosition, newPosition);
			}
						
		}
		
	}
	
}
