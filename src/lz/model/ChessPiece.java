package lz.model;

import lz.action.Movement;

public abstract class ChessPiece {
	
	//chess piece name
	protected String name;
	//black or white, black = 0, white = 1
	protected int color;
	
	public ChessPiece( String name, int color)
	{
		this.name = name;
		this.color = color;
	}
	
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	//get the color of the chess
	public int getColor()
	{
		return color;
	}
	
	//check if the piece can move in this way
	public abstract boolean canIMoveThisWay( Movement movement, Board board);
	
	

}
