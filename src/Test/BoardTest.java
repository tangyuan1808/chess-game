package Test;
import java.awt.Checkbox;

import org.junit.Test;

import lz.action.Movement;
import lz.model.Bishop;
import lz.model.Board;
import lz.model.King;
import lz.model.Pawn;
import lz.model.Queen;
import lz.model.Rook;

public class BoardTest {

	public final static int black = 0;
	public final static int white = 1;	
	
	/*
	@Test
	public void BoardInitial()
	{
		Board board = new Board();
		board.printBoard();	
	}
	*/
	
	/*
	@Test
	public void TestinCheck()
	{
		Board board = new Board( 8, 8);
		
		//black king 
		King blackKing = new King( "king", 0 );
		//white king
		King whiteKing = new King( "king", 1);
		//Black pawn
		Pawn blackPawn = new Pawn("pawn", 0);
		//White pawn
		Pawn whitePawn = new Pawn("pawn", 1);
		
		board.setChessPiece(3, 3, blackKing);
		board.setChessPiece(4, 3, whitePawn);
		
		board.setChessPiece(4, 2, whiteKing);
		board.setChessPiece(3, 2, blackPawn);
			
	
		System.out.println(board.inCheck(black, board));
		System.out.println(board.inCheck(white, board));
		
		
		board.printBoard();
		
	}
	
	
	/*
	@Test
	public void TestCheckMate()
	{
		Board board = new Board( 8, 8);
		
		//black king 
		King blackKing = new King( "king", 0 );
		//white king
		King whiteKing = new King( "king", 1);
		//white rook
		Rook rook = new Rook("rook", 1);
	
		
		board.setChessPiece(5, 7, blackKing);
		board.setChessPiece(5, 5, whiteKing);
		board.setChessPiece(7, 7, rook);
			
		board.printBoard();
		System.out.println(" ");

		
		if( board.checkMate(black, board)  ){	
			System.out.println("good for Checkmate");
		}
		
		
		
		
	}
	*/
	
	
	@Test
	public void TestStaleMate()
	{
		Board board = new Board( 8, 8);
		
		//black king 
		King blackKing = new King( "king", 0 );
		//white king
		King whiteKing = new King( "king", 1);
		//white rook
		Queen queen= new Queen("queen", 1);
	
		
		
		board.setChessPiece(0, 7, blackKing);
		board.setChessPiece(1, 5, whiteKing);
		board.setChessPiece(2, 6, queen);
			
		if( board.staleMate(black, board) ){	
			System.out.println("good for stalemate");
		}
		board.printBoard();
		
	}
	
}
