package org.jointheleague.discord_bot_example;

public class Cell {

	String type;
	int x;
	int y;
	private boolean explored;
	
	public Cell(String type, int x, int y) {
		explored = false;
	}
	
	public void explore() {
		explored = true;
	}
	
	public boolean isExplored() {
		return explored;
	}

}
