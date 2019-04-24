package lz.model;

import lz.action.Movement;

public class Pawn extends ChessPiece{

	// 1 first move
	// 0 otherwise
	int firstTime;
	
	public Pawn(String name, int color) {
		super(name, color);
		this.firstTime = 1;
		// TODO Auto-generated constructor stub
	}

	//update to see if it is the first move
	public void setFirstTime(int firstTime) {
		this.firstTime = firstTime;
	}
	
	public int getFirstTime() {
		return firstTime;
	}

	@Override
	public boolean canIMoveThisWay(Movement movement, Board board) {
		
		int startX = movement.getStartX();
		int startY = movement.getStartY();
		int endX = movement.getEndX();
		int endY = movement.getEndY();
		
		int diff1 = endX - startX;
		int diff2 = endY - startY;
		
		//black
		int direction = 1;
		//white
		if( this.color == 1 )
			direction = -1;
		
		//I do move and within the correct bond
		if( board.checkCoordinate(endX, endY) == true && movement.doIMove(startX, startY, endX, endY) )
		{

			//normal move, only move vertically 
			if( Math.abs(diff2) == 0 ) 
			{
				//first time to move, and not killing
				if( this.firstTime == 1  )
				{
					//System.out.println("first move pawn in check move");
					
					//must move less than 2 steps and in the right direction
					if( ( diff1 )*direction <= 2 && ( diff1 )*direction > 0   )
					{	
						System.out.println("something happened");
						//if nothing on my way
						if( board.getFromBoard(startX + direction, startY ) == null &&  board.getFromBoard(endX, endY) == null)
						{
							return true;
						}
					}	
				}
				
				//not first time, move one step 
				else if(  ( diff1 )*direction == 1 && board.getFromBoard(endX, endY) == null  )
				{
					return true;
				}
				
			}
			
			//killing
			else if( Math.abs( diff2 ) == 1 && diff1 == direction ) 
			{
				//kill the right color
				if( board.getFromBoard(endX, endY) != null 
						&& board.getFromBoard(endX, endY).getColor() + board.getFromBoard(startX, startY).getColor() == 1 )
					return true;
			}
		
		}
		
		return false;
	}

	
	
	
}
