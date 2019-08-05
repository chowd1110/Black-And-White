package checkers;

public class blackCheckerStrategy {
	private CheckerBoard model;
	
	public blackCheckerStrategy(CheckerBoard board) {
		this.model = board;
	}

	public void moveLeft(Checker checker) {
		
		int[] currentPosition = checker.getPosition();
		int[] newPosition =  {currentPosition[0] - 1 , currentPosition[1] + 1}; // CHECK
		// check if newPosition is inside the board here
		if (!(this.isCheckerWithinBoard(newPosition))) {
			MessageBox.createMessageBox("Checker cannot be moved any further left", 
					"Checker Cannot be Moved");
			return;
		}
		
		String checkerAtNewPosition = this.model.getCheckerAtPosition(newPosition);
		
		
		if(checkerAtNewPosition == checker.getColour()) { 
			MessageBox.createMessageBox("There is already a black checker at that position",
					"Checker Cannot be Moved");
			
		}
		
		else {
			
			if (checkerAtNewPosition == "Empty") { // there is neither a white or black checker at newPosition
				checker.setPosition(newPosition);
				this.model.updateBoard(checker, currentPosition);
			}
			else { // there is a white checker at newPosition. Make a Jump
	
			    this.jumpLeft(checker, newPosition);
				
			}
						
		}
	}
	
	public void jumpLeft(Checker checker, int[] jumpedCheckerPosition ) {
		
	   
		int[] checkerPositionAfterJumping = {jumpedCheckerPosition[0] - 1 , 
				jumpedCheckerPosition[1] + 1}; //CHECK
		
		if (!(this.isCheckerWithinBoard(checkerPositionAfterJumping))) {
			MessageBox.createMessageBox("You're out of real estate on the board", 
					"Checker Cannot be Moved");
			return;
		}
		
		if (this.model.getCheckerAtPosition(checkerPositionAfterJumping) == "Empty") {
			
		    int[] currentPosition = checker.getPosition();
		    checker.setPosition(checkerPositionAfterJumping);
		    checker.setJumped(true);
		    this.model.updateBoard(checker, currentPosition, jumpedCheckerPosition);
		}
		else { // there is a checker at checkerPositionAfterJumping
			MessageBox.createMessageBox("Cannot make jump", "Checker Cannot be Moved");
		
		}
		
	}
	
	public boolean isCheckerWithinBoard(int[] position) {
		int column = position[0];
		int row = position[1];
		
		if ((0 <= column && column <= 7) && (0 <= row && row <= 7)) {
			return true;
		}
		else {
		    return false;
		}
	}
	
}
