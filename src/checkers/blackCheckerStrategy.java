package checkers;

public class blackCheckerStrategy {
	private CheckerBoard model;
	
	public blackCheckerStrategy(CheckerBoard board) {
		this.model = board;
	}
	
	public void move(Checker checker, String direction) {
		int[] currentPosition = checker.getPosition();
		int[] newPosition =  calcNewPosition(currentPosition, direction);
		if (!(this.isCheckerWithinBoard(newPosition))) {
			MessageBox.createMessageBox("The selected Checker Cannot be Moved", 
					"You ran out of real estate");
			return;
		}
		
		String checkerAtNewPosition = this.model.getCheckerAtPosition(newPosition);
		
		if(checkerAtNewPosition == checker.getColour()) { 
			MessageBox.createMessageBox("The selected Checker Cannot be Moved", 
					"There is already a black checker at that position");
		}
		
        else {
			
			if (checkerAtNewPosition == "Empty") { // there is neither a white or black checker at newPosition
				checker.setPosition(newPosition);
				this.model.updateBoard(checker, currentPosition);
			}
			else { // there is a white checker at newPosition. Make a Jump
				this.jump(checker, direction, newPosition);
				
			}
						
		}		
	}
	
	
	
	private int[] calcNewPosition(int[] position, String direction) {
		
		if (direction == "left") {
			int[] newPosition =  {position[0] - 1 , position[1] + 1};
			return newPosition;
		
		}
		else { // direction == "right"
			int[] newPosition =  {position[0] + 1 , position[1] + 1};
			return newPosition;
			
		}	
		
	}
	
    private void jump(Checker checker, String direction, int[] jumpedCheckerPosition) {
    	
    	int[] checkerPositionAfterJumping = calcNewPosition(jumpedCheckerPosition, direction);
    	
    	if (!(this.isCheckerWithinBoard(checkerPositionAfterJumping))) {
			MessageBox.createMessageBox("The Selected Checker Cannot be Moved" , 
					"You're out of real estate on the board");
			return;
		}
    	
    	
		if (this.model.getCheckerAtPosition(checkerPositionAfterJumping) == "Empty") {
			
		    int[] currentPosition = checker.getPosition();
		    checker.setPosition(checkerPositionAfterJumping);
		    checker.setJumped(true);
		    this.model.updateBoard(checker, currentPosition, jumpedCheckerPosition);
		}
		else { // there is a checker at checkerPositionAfterJumping
			MessageBox.createMessageBox("The Selected Checker Cannot be Moved", 
					"You Cannot Jump this Checker");
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
