package lz.model;

import lz.action.Movement;

public class King extends ChessPiece {

	public King(String name, int color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canIMoveThisWay(Movement movement, Board board) {
		
		int startX = movement.getStartX();
		int startY = movement.getStartY();
		int endX = movement.getEndX();
		int endY = movement.getEndY();
		
		//System.out.println(startX+" "+startY+ " "+endX+ " "+endY);
		

		if( board.checkCoordinate(endX, endY) == true && movement.doIMove(startX, startY, endX, endY) )
		{
			//System.out.println("legal position" );
			if( Math.abs(endX-startX) <= 1 && Math.abs(endY-startY) <= 1  )
			{
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
