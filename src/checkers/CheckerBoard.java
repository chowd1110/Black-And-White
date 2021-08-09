package checkers;

import java.util.ArrayList;
import java.util.Observable;

/**
 * The "model" in our MVC design. It represents the state of the game with the help of
 * an 8x8 array of Strings. The CheckerBoard is made Observable and the View class
 * is an observer of the CheckerBoard. This way, if any change is made to the 
 * CheckerBoard, we can convey that information to the view using the notifyObservers()
 * method of the Observable class.
 * Attributes:
 *     board: An 8x8 array of strings that represents the state of our Checker board
 *            at any point in time. We use "Black" and "White" to represent black and
 *            white checkers respectively. If there is neither a black or a white checker
 *            at any index, we use "Empty."
 *     numBlackCheckers: Number of black checkers remaining in our current game.
 *     numWhiteCheckers: Number of white checkers remaining in our current game.
 *     blackCheckerPos: An ArrayList of 2x2 int arrays. The int arrays are the
 *                      coordinates of the black checkers in our board.
 *     whiteCheckerPos: An ArrayList of 2x2 int arrays. The int arrays are the
 *                      coordinates of the white checkers in our board.                 
 * @author Saadaf Chowdhury
 */

public class CheckerBoard extends Observable {
	private String[][] board = new String[8][8];
	private int numBlackCheckers = 12;
	private int numWhiteCheckers = 12;
	private ArrayList <int[]> blackCheckerPos = new ArrayList<int[]>(); 
	private ArrayList <int[]> whiteCheckerPos = new ArrayList<int[]>(); 
	
	/**
	 * Initialize a new checker board with the black and white pieces in their 
	 * respective starting positions.
	 */
	
	public CheckerBoard() {
		
		this.initEmptyBoard(); 
		
		int[][] blackCheckerPos = {{0,1},{1,0},{1,2},{2,1},{3,0},
				{3,2},{4,1},{5,0},{5,2},{6,1},{7,0},{7,2}};
		this.addCheckers(blackCheckerPos, "Black");
		
		int[][] whiteCheckerPos = {{0,5},{0,7},{1,6},{2,5},{2,7},{3,6},{4,5},
				{4,7},{5,6},{6,5},{6,7},{7,6}};
		this.addCheckers(whiteCheckerPos, "White");	
	}
    
	/**
	 * Fill up our board array with "Empty" at each index. "Empty" implies that no
	 * Black or White Checker is at that position.
	 */
	private void initEmptyBoard() {
		
		for (int i = 0; i < this.board.length; i++ ) {
			for (int j = 0; j < this.board.length; j++) {
				this.board[i][j] = "Empty"; /*empty = that position on the board
				 does not contain a checker*/
			}
		}
	}
	
	/**
	 * Populate our board array with "Black" or "White" based on the starting
	 * positions of the black and white checkers at the start of the game.
	 * @param positions: A 2-d nested int array that contains the positions where
	 *                   checkers with colour `colour` are placed at 
	 * @param colour: The colour of the checker pieces to add.
	 */
	private void addCheckers(int[][] positions, String colour) {
		int column;
		int row;
		
		for (int i = 0; i < positions.length; i++) {
			if (colour == "Black") {
				this.blackCheckerPos.add(positions[i]);
			}
			else {
				this.whiteCheckerPos.add(positions[i]);
			}
			column = positions[i][0];
			row = positions[i][1];
			this.board[column][row] = colour;	
		}
	}
	
	/**
	 * Update the position of a checker piece on the board and notify the
	 * View of the change.
	 * @param checker: The checker whose position is to be updated.
	 * @param previousPosition: The previous position of the checker piece.
	 */

	public void updateBoard(Checker checker, int[] previousPosition) {
		
		this.removeCheckerAtPosition(previousPosition); // remove checker from old position
		this.addCheckerAtPosition(checker.getColour(), checker.getPosition());
		// ask View to update itself
		this.setChanged();
		this.notifyObservers(checker); 
	}
	
	/**
	 * The overloaded version of updateBoard() is used to update our board when
	 * a checker is making a jump.
	 * @param checker: The checker whose position is to be updated(I think).
	 * @param previousPosition
	 * @param checkerToJump: The position of the checker that is being jumped.
	 *                       We will remove this from our board.
	 */
	
	public void updateBoard(Checker checker, int[] previousPosition, int[] checkerToJump) {
		
		this.removeCheckerAtPosition(previousPosition);
		this.removeCheckerAtPosition(checkerToJump);
		this.decrementNumChecker(this.getCheckerAtPosition(checkerToJump)); // decrement the checker-count for the jumped checker
		this.addCheckerAtPosition(checker.getColour(), checker.getPosition());
        this.setChanged();
        this.notifyObservers(checker);     
	}
	
	/**
	 * Get the checker piece at a position. Will also return "Empty" if
	 * there is no checker at the specified position.
	 * @param position: The position whose content this method returns.
	 * @return
	 */
	
	public String getCheckerAtPosition(int[] position) {
		int column = position[0];
		int row = position[1];
		return this.board[column][row];
		
	}
	
	/**
	 * Remove a checker from our board by setting where the piece previously was 
	 * to "Empty"
	 * @param position: The position to remove the checker from.
	 */
	
	public void removeCheckerAtPosition(int[] position) {
		
		int column = position[0];
		int row = position[1];
		this.board[column][row] = "Empty";
	}
	
	/**
	 * Add checker of a specific colour to a specific position on our board.
	 * @param colour: Colour of the checker to add.
	 * @param position: The position where the checker is addded.
	 */
	
	private void addCheckerAtPosition(String colour, int[] position ) {
		
		int column = position[0];
		int row = position[1];
		this.board[column][row] = colour;
		
	}
	
	/**
	 * Decrement the count of the checkers of the specified colour. This method
	 * is usually called after a checker has been captured.
	 * @param colour The colour of the checker whose count is decremented.
	 */
	
	private void decrementNumChecker(String colour) {
		if (colour == "Black") {
			this.setNumBlackCheckers(this.getNumBlackCheckers() - 1);
		}
		else {
			this.setNumWhiteCheckers(this.getNumWhiteCheckers() - 1);
		}
	}
	
	
	public String[][] getBoard() {
		return board;
	}

	public int getNumBlackCheckers() {
		return numBlackCheckers;
	}
	
	public void setNumBlackCheckers(int numBlackCheckers) {
		this.numBlackCheckers = numBlackCheckers;
	}

	public int getNumWhiteCheckers() {
		return numWhiteCheckers;
	}
	
	public void setNumWhiteCheckers(int numWhiteCheckers) {
		this.numWhiteCheckers = numWhiteCheckers;
	}

	public ArrayList<int[]> getBlackCheckerPos() {
		return blackCheckerPos;
	}

	public ArrayList<int[]> getWhiteCheckerPos() {
		return whiteCheckerPos;
	}
}