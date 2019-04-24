package lz.model;

import lz.action.Movement;

public class Bishop extends ChessPiece{

	public Bishop(String name, int color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean canIMoveThisWay(Movement movement, Board board) {
		
		int startX = movement.getStartX();
		int startY = movement.getStartY();
		int endX = movement.getEndX();
		int endY = movement.getEndY();
		
		//vertical distance
		int diff1 = endX - startX;
		//horizontal distance
		int diff2 = endY - startY;
		
		if( board.checkCoordinate(endX, endY) == true && movement.doIMove(startX, startY, endX, endY) )
		{
			if( Math.abs(diff1) == Math.abs(diff2) )
			{
				if( checkExist( startX, startY, endX, endY, board) == true )
				{
					System.out.println("I can move here" );
					
					if( board.getFromBoard(endX, endY) != null )
					{
						if( board.getFromBoard(endX, endY).getColor() + board.getFromBoard(startX, startY).getColor() == 1 )
							return true;
					}
					else	
						return true;
				}
			}
		}
		return false;
	}


	private boolean checkExist(int startX, int startY, int endX, int endY, Board board) {
		
		int diff = Math.abs( startX - endX);
		int ct = 1;
		
		int x = startX;
		int y = startY;
		
		int pt1 = 1;
		int pt2 = 1;
		
		
		System.out.println( startX + " " +  startY + " to "+ endX + " " + endY);
		
		if( startX > endX )
			pt1 = -1;
		
		if( startY > endY )
			pt2 = -1;
		
		while( ct < diff )
		{
			
			
			x += pt1;
			y += pt2;

			System.out.println( x + " " +  y);
			
			if(  board.checkChessPieceExist( x, y ) == true )
						return false;	
			
			ct++;
		}
		
		return true;
	}


	
	
}
