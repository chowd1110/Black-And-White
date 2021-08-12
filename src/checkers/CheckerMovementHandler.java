package checkers;

/**
 * The CheckerMovementHandler class is used when the user moves a checker piece.
 * The class handles both jumps and regular movements. It asks the model to update
 * itself after a move is made.
 * 
 * @author Saadaf Chowdhury
 *
 */

public class CheckerMovementHandler {
	private CheckerBoard model;
	private Game game;
	
	public CheckerMovementHandler(CheckerBoard board) {
		this.model = board;
	}
	
	/**
	 * Move the checker (or make a jump) in the specified direction if the move/jump
	 * is possible. Otherwise, notify the user that a their move is invalid.
	 * @param checker
	 * @param direction
	 */
	
	public void move(Checker checker, String direction) {
		
		int[] currentPosition = checker.getPosition();
		int[] newPosition =  this.calcNewPosition(currentPosition, direction, 
				checker.getColour()); // new position is where the checker 
		                          // piece would be if the move is successful
		
		if (!(this.isCheckerWithinBoard(newPosition))) { // making this move 
			                         // would take our checker out of the board
			MessageBox.createMessageBox("The selected Checker Cannot be Moved", 
					"You ran out of real estate");
			return;
		}
		
		// use the model to get the checker piece currently at at newPosition.
		String checkerAtNewPosition = this.model.getCheckerAtPosition(newPosition);
	
		if(checkerAtNewPosition == checker.getColour()) { 
			MessageBox.createMessageBox("The selected Checker Cannot be Moved", 
					"There is already a same-coloured checker at that position"); // change "same-coloured" to "Black" or "White"
		}
        else {	
			if (checkerAtNewPosition == "Empty") { // there is neither a white or black checker at newPosition
				if (this.game.isMoveAfterAJump()) {
	        		MessageBox.createMessageBox("You Cannot Move in this Direction", 
	        				"You have to Complete the Jump" );
	        		return;
	        	}
				checker.setPosition(newPosition);
				this.model.updateBoard(checker, currentPosition);
				this.game.changeCurrentPlayer();
			}
			else { // there is a different coloured checker at newPosition. Make a Jump
				this.jump(checker, direction, newPosition);
			}		
		}		
	}
	
	/**
	 * Calculate new position on the board if user moves piece from its current 
	 * position based on direction. This method works for White Checker pieces only.
	 * @param position: The initial position of the checker piece that will 
	 *                  potentially be moved.
	 * @param direction The direction the piece will be moved: either "left or "right."
	 * @return the new position the checker piece will be at if the move is successful.
	 */
	
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
	
	/**
	 * Overload of calcNewPosition method defined above. Has the same function 
	 * as the other version except this one is used for Black Checker pieces.
	 * @param position: The initial position of the checker piece that will 
	 *                  potentially be moved.
	 * @param direction: The direction the piece will be moved: either "left or "right."
	 * @param colour: If colour is "White," we simply call the other version of this 
	 *                method.
	 * @return the new position the checker piece will be at if the move is successful.
	 */
	
	public int[] calcNewPosition(int[] position, String direction, String colour) { // for black checkers
		
		if (colour == "White") {
			int[] newPosition = this.calcNewPosition(position,direction);
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
	
	/**
	 * Make a jump and ask the model to update itself after the jump is completed.
	 * @param checker: The checker that is making the jump.
	 * @param direction: The direction in which the checker that is making the
	 *                   jump is moving.
	 * @param jumpedCheckerPosition: The position of the checker that is getting jumped.
	 */
	
    private void jump(Checker checker, String direction, int[] jumpedCheckerPosition) {
    	
    	// the position of `checker` after it completes the jump.
    	int[] checkerPositionAfterJumping = calcNewPosition(jumpedCheckerPosition, direction,  
    			checker.getColour());
    	
    	if (!(this.isCheckerWithinBoard(checkerPositionAfterJumping))) {
			MessageBox.createMessageBox("The Selected Checker Cannot be Moved" , 
					"You're out of real estate on the board");
			return;
		}
    	
		if (this.model.getCheckerAtPosition(checkerPositionAfterJumping) == "Empty") { // make a jump
			
		    int[] currentPosition = checker.getPosition();
		    checker.setPosition(checkerPositionAfterJumping);
		    checker.setJumped(true);
		    checker.setJumpedCheckerPosition(jumpedCheckerPosition); // not sure why setJumpedCheckerPosition attribute is necessary in the model.
		    
		    if (!(this.isJumpAvailable(checker.getColour(), checkerPositionAfterJumping))) {
		    	this.game.changeCurrentPlayer();
		    }
		    this.model.updateBoard(checker, currentPosition, jumpedCheckerPosition);
		}
		else { // there is a checker at checkerPositionAfterJumping
			MessageBox.createMessageBox("The Selected Checker Cannot be Moved", 
					"You Cannot Jump this Checker");
		}
	}
    
    /**
     * Return true if the position is within our board.
     * @param position
     * @return
     */

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
	
	/**
	 * Check if a jump is available after a player has made a move. Set
	 * the value of this.game.setMoveAfterAJump appropriately.
	 * @param colour
	 * @param checkerPosition
	 * @return
	 */
	
	public boolean isJumpAvailable(String colour, int[] checkerPosition) {
		String oppColour;
		
		if (colour == "White") {
			oppColour = "Black";
		}
		else {
			oppColour = "White";
		}
		
		int[] checkerDiagonalRight = this.calcNewPosition(checkerPosition, "right", colour);
		int[] checkerDiagonalLeft = this.calcNewPosition(checkerPosition, "left", colour);
		int[] twoSpacesDiagonallyRight = this.calcNewPosition(checkerDiagonalRight, "right", colour);
		int[] twoSpacesDiagonallyLeft = this.calcNewPosition(checkerDiagonalLeft, "left", colour);
		
		
		if (this.isCheckerWithinBoard(checkerDiagonalRight) && 
				this.model.getCheckerAtPosition(checkerDiagonalRight) == oppColour &&
				this.isCheckerWithinBoard(twoSpacesDiagonallyRight) &&
				this.model.getCheckerAtPosition(twoSpacesDiagonallyRight) == "Empty") {
			
			this.game.setMoveAfterAJump(true);
			return true;
		}
		
		if (this.isCheckerWithinBoard(checkerDiagonalLeft) &&
			this.model.getCheckerAtPosition(checkerDiagonalLeft) == oppColour &&
			this.isCheckerWithinBoard(twoSpacesDiagonallyLeft) && 
			this.model.getCheckerAtPosition(checkerDiagonalLeft) == "Empty") {
			
			this.game.setMoveAfterAJump(true);
			return true;
		}
		
		this.game.setMoveAfterAJump(false);

		return false;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
