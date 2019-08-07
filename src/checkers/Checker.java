package checkers;

import javafx.scene.control.Button;

public class Checker extends Button {	
	private int[] position;
	private String colour;
	private boolean jumped;
	private int[] jumpedCheckerPosition;
	
	public Checker(int[] pos, String colour) {
		this.position = pos;
		this.colour = colour;
		this.jumped = false;
		
	}
	
	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public boolean hasJumped() {
		return jumped;
	}

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
