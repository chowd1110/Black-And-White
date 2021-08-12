package checkers;

import javafx.scene.control.Button;

/**
 * The Checker class represents a piece on our CheckerBoard. At the start of 
 * the game we will have 24 checker pieces in total: 12 black and 12 white.
 * Attributes:
 *   position: A size-2 int array that determines the position of this checker 
 *             piece on our 8x8 Board. If the position is {3,7} then this piece
 *             is on the 4th row and 8th column (we start rows and columns from 0).
 *   colour: Colour of this checker piece (either "Black" or "White").
 *   jumped: Has this checker made a jump yet. At the start of the program it is
 *           set to false.
 *   jumpedCheckerPosition: The position of the checker that this piece has jumped over.
 *   
 * @author Saadaf Chowdhury
 */

public class Checker extends Button {	
	private int[] position;
	private String colour;
	private boolean jumped;
	private int[] jumpedCheckerPosition;
	
	/**
	 * Constructor for Checker.
	 * @param pos: an int array of size 2 representing where the checker piece
	 * starts with on our board.
	 * @param colour: colour of this checker piece: either "Black or "White".
	 */
	
	public Checker(int[] pos, String colour) {
		this.position = pos;
		this.colour = colour;
		this.jumped = false;
		
	}
	
	/**
	 * Get the position of this checker.
	 * @return the position of this checker on our board
	 */
	
	public int[] getPosition() {
		return position;
	}
	
	/**
	 * Set the new position of this checker piece.
	 * @param position: the new position to be set.
	 */

	public void setPosition(int[] position) {
		this.position = position;
	}
	
	/**
	 * Get the colour of this checker.
	 * @return "Black" or "White" i.e. the colour of this piece.
	 */

	public String getColour() {
		return colour;
	}
	
	/**
	 * Don't know if we need a setter for the colour; might delete later.
	 * @param colour
	 */

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	/**
	 * @return true if this checker has moved, false otherwise.
	 */

	public boolean hasJumped() {
		return jumped;
	}
	
	/**
	 * Set the jumped property when this checker makes a jump
	 * @param jumped: set to true if this piece has made a jump.
	 */

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}
	
	public int[] getJumpedCheckerPosition() {
		return jumpedCheckerPosition;
	}

	public void setJumpedCheckerPosition(int[] jumpedCheckerPosition) {
		this.jumpedCheckerPosition = jumpedCheckerPosition;
	}
}
