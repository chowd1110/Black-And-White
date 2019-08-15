package checkers;

public class CheckerMovementHandler {
	private CheckerBoard model;
	private Game game;
	
	public CheckerMovementHandler(CheckerBoard board) {
		this.model = board;
	}
	
	public void move(Checker checker, String direction) {
		
		int[] currentPosition = checker.getPosition();
		int[] newPosition =  calcNewPosition(currentPosition, direction, checker.getColour());
		if (!(this.isCheckerWithinBoard(newPosition))) {
			MessageBox.createMessageBox("The selected Checker Cannot be Moved", 
					"You ran out of real estate");
			return;
		}
		
		String checkerAtNewPosition = this.model.getCheckerAtPosition(newPosition);
		
		
		if(checkerAtNewPosition == checker.getColour()) { 
			MessageBox.createMessageBox("The selected Checker Cannot be Moved", 
					"There is already a same-coloured checker at that position");
		}
		
        else {
			
			if (checkerAtNewPosition == "Empty") { // there is neither a white or black checker at newPosition
				checker.setPosition(newPosition);
				this.model.updateBoard(checker, currentPosition);
				this.game.changeCurrentPlayer();
			}
			else { // there is a different coloured checker at newPosition. Make a Jump
				this.jump(checker, direction, newPosition);
				
			}
						
		}		
	}
	
	public int[] calcNewPosition(int[] position, String direction) {
	
		if (direction == "left") {
			int[] newPosition =  { position[0] - 1 , position[1] - 1 };
			return newPosition;

		}
		else { // direction == "right"
			int[] newPosition =  {position[0] + 1 , position[1] - 1};
			return newPosition;
			
		}	
	
	}
	
	public int[] calcNewPosition(int[] position, String direction, String colour) { // for black checkers
		if (colour == "White") {
			int[] newPosition = calcNewPosition(position,direction);
			return newPosition;
		}
		
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
    	
    	int[] checkerPositionAfterJumping = calcNewPosition(jumpedCheckerPosition, direction,  
    			checker.getColour());
    	
    	if (!(this.isCheckerWithinBoard(checkerPositionAfterJumping))) {
			MessageBox.createMessageBox("The Selected Checker Cannot be Moved" , 
					"You're out of real estate on the board");
			return;
		}
    	
    	
		if (this.model.getCheckerAtPosition(checkerPositionAfterJumping) == "Empty") {
			
		    int[] currentPosition = checker.getPosition();
		    checker.setPosition(checkerPositionAfterJumping);
		    checker.setJumped(true);
		    checker.setJumpedCheckerPosition(jumpedCheckerPosition);
		    this.model.updateBoard(checker, currentPosition, jumpedCheckerPosition);
		    if (!(this.isJumpAvailable(checker.getColour(), checkerPositionAfterJumping))){
		    	this.game.changeCurrentPlayer();
		    }
		    
		    
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
	
	public boolean isJumpAvailable(String colour, int[] checkerPosition) {
		String oppColour;
		
		if (colour == "White") {
			oppColour = "Black";
		}
		else {
			oppColour = "White";
		}
		
		int[] checkerDiagonalRight = this.calcNewPosition(checkerPosition, "right");
		int[] checkerDiagonalLeft = this.calcNewPosition(checkerPosition, "left");
		int[] twoSpacesDiagonallyRight = this.calcNewPosition(checkerDiagonalRight, "right");
		int[] twoSpacesDiagonallyLeft = this.calcNewPosition(checkerDiagonalLeft, "left");
		
		
		if (this.isCheckerWithinBoard(checkerDiagonalRight) && 
				this.model.getCheckerAtPosition(checkerDiagonalRight) == oppColour &&
				this.isCheckerWithinBoard(twoSpacesDiagonallyRight) &&
				this.model.getCheckerAtPosition(twoSpacesDiagonallyRight) == "Empty") {
			
			return true;
		}
		if (this.isCheckerWithinBoard(checkerDiagonalLeft) &&
			this.model.getCheckerAtPosition(checkerDiagonalLeft) == oppColour &&
			this.isCheckerWithinBoard(twoSpacesDiagonallyLeft) && 
			this.model.getCheckerAtPosition(checkerDiagonalLeft) == "Empty") {
				return true;
		}
		

		return false;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
}
