package lz.model;

import lz.action.Movement;

public class Queen extends ChessPiece{

	public Queen(String name, int color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canIMoveThisWay(Movement movement, Board board) {
		
		Rook rook = new Rook("rook", 0);
		Bishop bishop = new Bishop("bishop", 0);
		
		if( rook.canIMoveThisWay(movement, board) || bishop.canIMoveThisWay(movement, board) )
			return true;
		
		return false;
		
	}

	
}
