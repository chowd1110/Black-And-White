package checkers;

import javafx.scene.control.Button;

public class Checker extends Button {
	private int[] position;
	private String colour;
	
	public Checker(int[] pos, String colour) {
		this.position = pos;
		this.colour = colour;
		
	}

}