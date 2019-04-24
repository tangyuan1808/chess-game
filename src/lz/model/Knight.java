package lz.model;

import lz.action.Movement;

public class Knight extends ChessPiece{

	public Knight(String name, int color) {
			super(name, color);
			// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canIMoveThisWay(Movement movement, Board board) {
		
		int startX = movement.getStartX();
		int startY = movement.getStartY();
		int endX = movement.getEndX();
		int endY = movement.getEndY();
		
		if( board.checkCoordinate(endX, endY) == true && movement.doIMove(startX, startY, endX, endY) )
		{
			if( ( Math.abs( startX - endX) == 2 && Math.abs( startY - endY ) == 1) ||
					( Math.abs( startX - endX) == 1 && Math.abs( startY - endY ) == 2) ) {
				
				if( board.getFromBoard(endX, endY) != null )
				{
					if( board.getFromBoard(endX, endY).getColor() + board.getFromBoard(startX, startY).getColor() == 1 )
						return true;
				}
				else	
					return true;
				
			}
		}
		return false;
	}

	
}
