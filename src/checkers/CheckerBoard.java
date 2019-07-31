package checkers;

import java.util.Observable;

public class CheckerBoard extends Observable {
	private String[][] board = new String[8][8];
	
	public CheckerBoard() {
		initEmptyBoard(); 
		
		int[][] blackCheckerPos = {{0,1},{1,0},{1,2},{2,1},{3,0},
				{3,2},{4,1},{5,0},{5,2},{6,1},{7,0},{7,2}};
		
		addCheckers(blackCheckerPos, "Black");
		
		int[][] whiteCheckerPos = {{0,5},{0,7},{1,6},{2,5},{2,7},{3,6},{4,5},
				{4,7},{5,6},{6,5},{6,7},{7,6}};
		
		addCheckers(whiteCheckerPos, "White");
		
	}
    
	private void initEmptyBoard() {
		
		for (int i = 0; i < this.board.length; i++ ) {
			for (int j = 0; j < this.board.length; j++) {
				this.board[i][j] = "Empty"; //  empty = that position on the board does not
				                       // contain a checker
			}
		}
	}
	
	private void addCheckers(int[][] positions, String Colour) {
		int column;
		int row;
		
		for (int i = 0; i < positions.length; i++) {
			column = positions[i][0];
			row = positions[i][1];
			this.board[column][row] = Colour;
			
		}
	}

	public void updateBoard(Checker checker, int[] newPosition) {
		int currentColumn = checker.getPosition()[0];
		int currentRow = checker.getPosition()[1];
		
		String colour = checker.getColour();
		// remove the checker from its current position
		this.board[currentColumn][currentRow] = "Empty";
		
		int newColumn = newPosition[0];
		int newRow = newPosition[1];
		// add the checker to its new position
		this.board[newColumn][newRow] = colour;
		// ask View to update itself
		this.setChanged();
		this.notifyObservers(checker);
		
	
		
		
	}
	
	
}
