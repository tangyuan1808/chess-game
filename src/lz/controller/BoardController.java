package lz.controller;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lz.action.Movement;
import lz.model.Board;
import lz.model.ChessPiece;
import lz.model.Pawn;
import lz.model.Player;
import lz.view.BoardPic;



public class BoardController extends JFrame{
	
	
	public final static int black = 0;
	public final static int white = 1;	

	private static final long serialVersionUID = 88L;
	JPanel jPanel;
	JButton[][] buttons;; //for chess piece
	JPanel bar;
	JButton[] tools; // 0-forfeit 1-restart 2-undo 
	JLabel[] labels; // 0-b name 1- b score 2-w name 3-w score

	
	ChessPiece chessToMove = null;
	int moveChessPieceXPosition;
	int moveChessPieceYPosition;
	int LastX;//x idx for last moved chess
	int LastY;//y idx for last moved chess
	ChessPiece replacedChessPiece;
	ChessPiece movedChessPiece;
	
	BoardPic boardPic;
	Board board;
	Player blackPlayer = new Player(black);
	Player whitePlayer = new Player(white);
	int turn;
	boolean undoFlag = false;
	
	
	public BoardController(Board board) {
		
		
		setSize(770,770);
		setLocationRelativeTo(null);  // set it to display in the middle
        setVisible(true);  
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.board = board;
		//board.printBoard();
		turn = white;
		boardPic = new BoardPic(board);
		
		jPanel = new JPanel();
		buttons = new JButton[8][8];
		bar = new JPanel();
		tools = new JButton[3];
		labels = new JLabel[5];
		jPanel.setLayout(null);
	    bar.setLayout(new GridLayout(10,5));
	    
	    labels[0] = playerName("Black Player");
	    labels[1] = score(black);
	    labels[2] = playerName("White Player");
	    labels[3] = score(white);
	    labels[4] = turn();
	    
	    forfeitTool();
	    restartTool();
	    undoTool();
	    
	    bar.add(labels[0]);
	    bar.add(labels[1]);
	    bar.add(tools[0]); //forfeit
	    bar.add(tools[1]); //restart
	    bar.add(tools[2]); //undo
	    bar.add(labels[2]);
	    bar.add(labels[3]);
	    bar.add(labels[4]);
	    add(bar, BorderLayout.EAST);  
		setupChessBoard();
	    
	}
	
	
	private void forfeitTool(){
			tools[0] = new JButton("FORFEIT");
			tools[0].setSize(38,38);
			tools[0].setOpaque(true);
			tools[0].addActionListener(new ActionListener(){		
				
	    		public void actionPerformed(ActionEvent e){	
	    				if(e.getSource() == tools[0] )
	    				{	
	    					resetScore(1-turn);
	    					//Board board = new Board();
	    					//new BoardController(board);
	    					resetBoard();
	    				}		
	    		}	
	    		});
	}
	

	private void restartTool(){
			tools[1] = new JButton("RESTART");
			tools[1].setSize(38,38);
			tools[1].setOpaque(true);
			tools[1].addActionListener(new ActionListener(){		
				
	    		public void actionPerformed(ActionEvent e){	
	    				if(e.getSource() == tools[1] )
	    				{	
	    					clearScore();
	    					//Board board = new Board();
	    					//new BoardController(board);
	    					resetBoard();
	    				}		
	    		}	
	    		});
	}
	
	
	private void undoTool(){
		
		tools[2] = new JButton("UNDO");
		tools[2].setSize(38,38);
		tools[2].setOpaque(true);
		tools[2].addActionListener(new ActionListener(){		
			
    		public void actionPerformed(ActionEvent e){	
    				if(e.getSource() == tools[2] )
    				{	
    					if( undoFlag == true)
    						putChessPieceBack();
    					switchTurn();
    				}		
    		}	
    		});
	}
	
	
	private void putChessPieceBack()
	{
		//undo on board
		board.setChessPiece(LastX, LastY, replacedChessPiece);
		board.setChessPiece(moveChessPieceXPosition, moveChessPieceYPosition, movedChessPiece);
		boardPic = new BoardPic(board);
		
		//pawn
		if( movedChessPiece.getName().equals("pawn") )
		{
			Pawn pawn = (Pawn) movedChessPiece;
			pawn.setFirstTime( pawn.getFirstTime() - 1 );
		}
		
		//resetIcon
		buttons[LastX][LastY].setIcon( boardPic.getIconFromBoardPic(LastX, LastY) );
		buttons[moveChessPieceXPosition][moveChessPieceYPosition].setIcon( boardPic.getIconFromBoardPic(moveChessPieceXPosition, moveChessPieceYPosition) );
	
		undoFlag = false;
	}
	
	private void clearScore()
	{
		blackPlayer.setScore(0);
		whitePlayer.setScore(0);
		labels[1].setText("score: " + getScore(black));
		labels[3].setText("score: " + getScore(white));

	}
	
	
	private void resetBoard()
	{
		this.turn = white;
		this.board = new Board();
		boardPic = new BoardPic(board);
		//board.printBoard();
		for(int i=0; i<board.getWidth();i++)
		{
			for(int j=0; j<board.getHeight();j++)
			{	
				buttons[i][j].setIcon( boardPic.getIconFromBoardPic(i,j) );
			}
		}				
	}
	
	
	
	private JLabel turn()
	{
		JLabel label = new JLabel("White Turn");
		label.setSize(88,88);
		label.setOpaque(true);  
		return label;
	}
	
	private JLabel playerName( String name) {
		//white player name bar	
		JLabel label = new JLabel(name);
		label.setSize(44, 88);
		label.setOpaque(true);  
		return label;
	}
	
	
	private void resetScore(int color)
	{
		System.out.println("forfeit color is " + color);
		changeScore(color);
		if( color == black )
			labels[1].setText("score: " + getScore(color));
		else
			labels[3].setText("score: " + getScore(color));
	}
	
	private int getScore(int color )
	{
		if( color == black ){
			System.out.print(blackPlayer);
			return blackPlayer.getScore();
		}
		
		else{
			System.out.print(blackPlayer);
			return whitePlayer.getScore();
		}	
	}
	
	private JLabel score( int color ){
		
		JLabel score = new JLabel ("score: " + getScore(color));
		score.setSize(44,88);
		score.setOpaque(true);  
		return score;
	}
	

	private void switchTurn()
	{
		if(this.turn == black){
			labels[4].setText("White Turn");
			this.turn = white;
		}
		else{
			labels[4].setText("Black Turn");
			this.turn = black;
		}
		
		//my turn to move, but in checkmate
		//other player socre++
		if( board.checkMate( turn, board ))
		{
			if( turn == black) 
				checkMate("black");
			else
				checkMate("white");
			resetScore(1-turn);
			resetBoard();
		}
		
		/*
		//if stalemate
		else if( board.staleMate(turn, board) ){
			if( turn == black) 
				staleMate("black");
			else
				staleMate("white");
			resetBoard();
		}
		*/	
		
	}
	
	
	private void changeScore( int color )
	{
		int score;
		System.out.println("change score");
		
		if( color == black ){	
			score = blackPlayer.getScore();
			blackPlayer.setScore(score + 1);
		}
		else{
			score = whitePlayer.getScore();
			whitePlayer.setScore(score + 1);
		}
	}

	public void setupChessBoard() {
	
    	jPanel.setLayout(new GridLayout(8,8));
		
		for(int i=0; i<board.getWidth();i++)
		{
			for(int j=0; j<board.getHeight();j++)
			{
				
				//set the color for grid
				Color color;
				if((i+j)%2 == 0)
					color = Color.darkGray;
				else 
					color = Color.lightGray;
				
				buttons[i][j] =  new JButton();
				buttons[i][j].setSize(77, 77);
				buttons[i][j].setLocation(j*77,i*77);
				buttons[i][j].setBackground(color);
				buttons[i][j].setOpaque(true);  
                
				//System.out.println(  boardPic.getIconFromBoardPic(i,j) );
               	//assign the pic
				buttons[i][j].setIcon( boardPic.getIconFromBoardPic(i,j) );
               
               	//set Bounder to black
				buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.black));  
                
                
				buttons[i][j].addActionListener(new ActionListener(){	

					public void actionPerformed(ActionEvent e){	
						
                		Object source = e.getSource();		
						for(int i=0; i<8;i++)
							for(int j=0; j<8;j++)
							{	
								if(source == buttons[i][j])
								{
									System.out.println("select:"+ i + ","+ j);
									doClick(i,j);
									return;				
								}	
							}				
                	}
                	}); 
                
                // add JButto to JPanel
                jPanel.add( buttons[i][j] );  
			 
                //add JPanel to JFrame
                add(jPanel, BorderLayout.CENTER);             
			}
		}  
	}
	
	
	private void wrongTerm()
	{
		JFrame tmp  =new JFrame("warning");
		tmp.setLocationRelativeTo(null);
		tmp.setVisible(true); 
		JOptionPane.showMessageDialog(tmp, "not your turn");
		tmp.setVisible(false); 	
	}
	
	private void invalidMove()
	{
		JFrame tmp  =new JFrame("warning");
		tmp.setLocationRelativeTo(null);
		tmp.setVisible(true); 
		JOptionPane.showMessageDialog(tmp, "invalid move");
		tmp.setVisible(false); 
	}
	
	private void inCheck( String color)
	{
		JFrame tmp  =new JFrame("warning");
		tmp.setLocationRelativeTo(null);
		tmp.setVisible(true); 
		JOptionPane.showMessageDialog(tmp, color +" in check");
		tmp.setVisible(false); 	
	}
	
	private void checkMate( String color)
	{
		JFrame tmp  =new JFrame("warning");
		tmp.setLocationRelativeTo(null);
		tmp.setVisible(true); 
		JOptionPane.showMessageDialog(tmp, color +" in checkmate");
		tmp.setVisible(false); 	
	}
	
	
	private void staleMate( String color)
	{
		JFrame tmp  =new JFrame("warning");
		tmp.setLocationRelativeTo(null);
		tmp.setVisible(true); 
		JOptionPane.showMessageDialog(tmp, color +" in stalemate");
		tmp.setVisible(false); 	
	}
	
	private void illegalMove( )
	{
		JFrame tmp  =new JFrame("warning");
		tmp.setLocationRelativeTo(null);
		tmp.setVisible(true); 
		JOptionPane.showMessageDialog(tmp, "illegal move");
		tmp.setVisible(false); 	
	}
	
	
	
	private void doClick( int x, int y )
	{
		
		
		//board.printBoard();
		//System.out.println(" ");
		
		//first click
		if(  chessToMove == null ){
			//click on nothing
			if( board.getFromBoard(x, y) == null )
				return;
			
			//click on the right color piece
			else if( board.getFromBoard(x, y) != null && turn == board.getFromBoard(x, y).getColor() ){	
				moveChessPieceXPosition = x;
				moveChessPieceYPosition = y;
				chessToMove = board.getFromBoard(x, y);
			}
			
			//click on wrong color
			else{
				wrongTerm();
				return;
			}
		}
		
		//Second click, already pick a right piece
		else{
			
			//System.out.println("second click");
			Movement movement = new Movement(moveChessPieceXPosition, moveChessPieceYPosition, x, y);
			
			// the move is valid
			if( chessToMove.canIMoveThisWay(movement, board) == true ){
				
				replacedChessPiece = board.getFromBoard(x, y); 
				movedChessPiece = chessToMove;
			
				//check if the move is legal
				Board newboard = new Board();
				newboard.copy(board);
				//move this chess piece on board
				newboard.setChessPiece(x, y, chessToMove);
				//System.out.println(" I move " + moveChessPieceXPosition + ":" + moveChessPieceYPosition);
				newboard.removeChessPieceFromBoard(moveChessPieceXPosition, moveChessPieceYPosition);
				if( newboard.inCheck( 1-turn, newboard)  ){
					illegalMove();
					chessToMove = null;
					//undoFlag = true;
					return;
				}
					
				
				//move this chess piece on board
				board.setChessPiece(x, y, chessToMove);
				
				//System.out.println(" I move " + moveChessPieceXPosition + ":" + moveChessPieceYPosition);
				board.removeChessPieceFromBoard(moveChessPieceXPosition, moveChessPieceYPosition);
				
				
				//update boardpic
				boardPic = new BoardPic(board);
				
				//update image for icon
				buttons[x][y].setIcon( boardPic.getIconFromBoardPic(x,y));
				buttons[moveChessPieceXPosition][moveChessPieceYPosition].setIcon(null);
			
				//tell if in check
				if( board.inCheck(turn, board) ){
					String color = "black";
					if( turn == black)
						color = "white";
					inCheck(color);	
				}
				
				
				if( chessToMove.getName().equals("pawn") )
				{
					Pawn pawn = (Pawn) chessToMove;
					pawn.setFirstTime( pawn.getFirstTime() + 1 );
				}
				
				
				//change turn after I move
				switchTurn();
				LastX = x;
				LastY = y;
				chessToMove = null;
				undoFlag = true;
				repaint(1,x,y,77,77);
				repaint(1, moveChessPieceXPosition,moveChessPieceYPosition, 77, 77);		
			}
			
			//not valid move
			else{
				invalidMove();
				chessToMove = null;
				return;
			}
		}
		
	}
	
}
