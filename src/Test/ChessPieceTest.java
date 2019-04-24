package Test;

import org.junit.Test;

import lz.action.Movement;
import lz.model.Bishop;
import lz.model.Board;
import lz.model.King;
import lz.model.Knight;
import lz.model.Pawn;
import lz.model.Rook;

public class ChessPieceTest {

	
	@Test
	public void KingTest()
	{
		Board board = new Board( 8, 8);
		
		King king = new King( "king", 0 );
		Pawn pawn1 = new Pawn("pawn", 0);
		Pawn pawn2 = new Pawn("pawn", 1);
		
		board.setChessPiece(0, 4, king);
		board.setChessPiece(1, 5, pawn1);
		board.setChessPiece(0, 3, pawn2);
		
		board.printBoard();
		
		Movement movement1 = new Movement( 0, 4, 0, 4);
		Movement movement2 = new Movement(0, 4, 1, 5);
		Movement movement3 = new Movement(0, 4, 0, 3);
		Movement movement4 = new Movement(0, 4, -1, 4);
		
		
		if( !king.canIMoveThisWay(movement1, board) && !king.canIMoveThisWay(movement2, board)
				&& king.canIMoveThisWay(movement3, board) && !king.canIMoveThisWay(movement4, board) )
			System.out.println("We good for King");
		
	}
	
	
	@Test
	public void KinghtTest()
	{
		Board board = new Board( 8, 8);
		
		Knight knight = new Knight("knight", 0);
		Pawn pawn1 = new Pawn("pawn", 0);
		Pawn pawn2 = new Pawn("pawn", 1);
		
		board.setChessPiece(1, 4, knight);
		board.setChessPiece(2, 2, pawn1);
		board.setChessPiece(3, 3, pawn2);
		
		board.printBoard();
		
		Movement movement1 = new Movement( 1, 4, 1, 4);
		Movement movement2 = new Movement(1, 4, 2, 2);
		Movement movement3 = new Movement(1, 4, 3, 3);
		Movement movement4 = new Movement(1, 4, -1, 3);
		
		
		if( !knight.canIMoveThisWay(movement1, board) && !knight.canIMoveThisWay(movement2, board)
				&& knight.canIMoveThisWay(movement3, board) && !knight.canIMoveThisWay(movement4, board) )
			System.out.println("We good for Knight");
		
	}
	
	
	@Test
	public void BishopTest()
	{
		Board board = new Board( 8, 8);
		
		Bishop bishop = new Bishop("bishop", 0);
		Pawn pawn1 = new Pawn("pawn", 0);
		Pawn pawn2 = new Pawn("pawn", 1);
		Pawn pawn3 = new Pawn("pawn", 1);
		
		
		board.setChessPiece(2, 2, bishop);
		board.setChessPiece(3, 3, pawn1);
		board.setChessPiece(4, 4, pawn2);
		board.setChessPiece(1, 1, pawn3);
		
		
		board.printBoard();
		
		Movement movement1 = new Movement( 2, 2, 2, 2);
		Movement movement2 = new Movement( 2, 2, 3, 3);
		Movement movement3 = new Movement( 2, 2, 4, 4);
		Movement movement4 = new Movement( 2, 2, 1, 1);
		
		
		if( !bishop.canIMoveThisWay(movement1, board) && !bishop.canIMoveThisWay(movement2, board) 
				&& !bishop.canIMoveThisWay(movement3, board) && bishop.canIMoveThisWay(movement4, board) )
			System.out.println("We good for Bishop");
		
	}
	
	
	
	@Test
	public void PawnTest()
	{
		Board board = new Board( 8, 8);
		
		Knight knight = new Knight("knight", 1);
		//black
		Pawn pawn1 = new Pawn("pawn", 0);
		//white
		Pawn pawn2 = new Pawn("pawn", 1);
		
		board.setChessPiece(3, 3, knight);
		board.setChessPiece(2, 2, pawn1);
		board.setChessPiece(6, 3, pawn2);
		
		board.printBoard();
		
		Movement movement1 = new Movement( 2, 2, 1, 2);
		Movement movement2 = new Movement(2, 2, 3, 3);
		Movement movement3 = new Movement(6, 3, 7, 3 );
		Movement movement4 = new Movement(6, 3, 5, 3);
		
		
		if( !pawn1.canIMoveThisWay(movement1, board)  && pawn1.canIMoveThisWay(movement2, board) 
				&& !pawn2.canIMoveThisWay(movement3, board)  && pawn2.canIMoveThisWay(movement4, board) )
			System.out.println("We good for Pawn");
		
	}
	
	
	
	@Test
	public void rook()
	{
		Board board = new Board( 8, 8);
		
		Rook rook = new Rook("rook", 0);
		Pawn pawn1 = new Pawn("pawn", 0);
		Pawn pawn2 = new Pawn("pawn", 1);
		Pawn pawn3 = new Pawn("pawn", 1);
		
		
		board.setChessPiece(2, 2, rook);
		board.setChessPiece(2, 5, pawn1);
		board.setChessPiece(5, 2, pawn2);
		board.setChessPiece(6, 2, pawn3);
		
		
		board.printBoard();
		
		Movement movement1 = new Movement( 2, 2, 2, 5);
		Movement movement2 = new Movement( 2, 2, 5, 2);
		Movement movement3 = new Movement( 2, 2, 6, 2);
		
		
		if( !rook.canIMoveThisWay(movement1, board) && rook.canIMoveThisWay(movement2, board) )
				//&& !rook.canIMoveThisWay(movement3, board)  )
			System.out.println("We good for Rook");
		
	}
	
	
	
}
