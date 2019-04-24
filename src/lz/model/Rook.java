package lz.model;

import lz.action.Movement;

public class Rook extends ChessPiece{

	public Rook(String name, int color) {
		super(name, color);
	
	}
	
	@Override
	public boolean canIMoveThisWay(Movement movement, Board board) {
		
		int startX = movement.getStartX();
		int startY = movement.getStartY();
		int endX = movement.getEndX();
		int endY = movement.getEndY();
		
		int verticalMove = endX - startX;
		int horizontalMove = endY - startY;
		
		if( board.checkCoordinate(endX, endY) == true && movement.doIMove(startX, startY, endX, endY) )
		{
			//move horizontal 
			if( verticalMove == 0)
			{
				if( checkPositionHorizontal( startY, endY, endX,  board ) == true )
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
			
			//move vertically
			else if( horizontalMove == 0)
			{
				//System.out.println( " v move");
				if( checkPositionVertical( startX, endX, endY, board ) == true )
				{

					//System.out.println( " I can move");
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
	
	private boolean checkPositionVertical(int startX, int endX, int endY, Board board) {
		
		if( endX > startX)
		{
			for( int i = startX + 1;  i < endX; i++  )
				if( board.checkChessPieceExist( i, endY ) )
					return false;
		}
		
		else if( endX < startX)
		{
			for( int i = endX + 1;  i < startX; i++  )
				if( board.checkChessPieceExist( i, endY ) )
					return false;
		}
		
		return true;
	}

	private boolean checkPositionHorizontal( int startY, int endY, int endX, Board board )
	{
		if( endY > startY)
		{
			for( int i = startY + 1;  i < endY; i++  )
				if( board.checkChessPieceExist( endX, i) )
					return false;
		}
		
		else if( endY < startY)
		{
			for( int i = endY + 1;  i < startY; i++  )
				if( board.checkChessPieceExist( endX, i) )
					return false;
		}
		
		return true;
	}

	
}
