package checkers;

import javafx.scene.control.Button;

public class Checker extends Button {	
	private int[] position;
	private String colour;
	
	public Checker(int[] pos, String colour) {
		this.position = pos;
		this.colour = colour;
		
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


	
	
	
	

}
