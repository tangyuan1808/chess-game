package lz.model;


import lz.action.Movement;

public class Board {

   public final static int black = 0;
   public final static int white = 1;	
	
	//A regular 8 * 8 board
	protected int width;
	protected int height;
	protected ChessPiece[][] chessPiece;
	
	//constructor
	public Board() {
		this.width = 8;
		this.height = 8;
		this.chessPiece = fillBoard();
	}
	
	//for test
	public Board( int width, int height )
	{
		this.width = width;
		this.height = height;
		this.chessPiece = new ChessPiece[8][8];
	}
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	//fill the board at beginning
	public ChessPiece[][] fillBoard()
	{
		chessPiece = new ChessPiece[8][8];
		
		chessPiece[0][0] = new Rook( "rook", black );
		chessPiece[0][1] = new Knight("knight", black );
		chessPiece[0][2] = new Bishop("bishop", black );
		chessPiece[0][4] = new King("king", black );
		chessPiece[0][3] = new Queen("queen", black );
		chessPiece[0][5] = new Bishop("bishop", black );
		chessPiece[0][6] = new Knight("knight", black );
		chessPiece[0][7] = new Rook("rook", black );
		
		chessPiece[7][0] = new Rook( "rook", white );
		chessPiece[7][1] = new Knight("knight", white );
		chessPiece[7][2] = new Bishop("bishop", white );
		chessPiece[7][4] = new King("king", white );
		chessPiece[7][3] = new Queen("queen", white );
		chessPiece[7][5] = new Bishop("bishop", white );
		chessPiece[7][6] = new Knight("knight", white );
		chessPiece[7][7] = new Rook("rook", white );
		
		for (int i = 0; i < 8; i++) {
			 chessPiece[1][i] = new Pawn("pawn", black);
		 chessPiece[6][i] = new Pawn("pawn", white);
		}
		
		return chessPiece;
	}
	
	
	//check if the coordinate is vaild in the board
	public boolean checkCoordinate( int x, int y ) 
	{
		if( x < 0 || x >= width || y < 0 || y >= height )
			return false;
		return true;
	}
	
	
	//get the chess piece from the board by its coordinate
	//if the coordinate is not correct, return null
	public ChessPiece getFromBoard(int x, int y)
	{
		if( checkCoordinate(x, y) == false)
			return null;
		return chessPiece[x][y];
	}
	
	//check if there is a chess piece at this coordinate
	public boolean checkChessPieceExist( int x, int y)
	{
		if(chessPiece[x][y] != null )
			return true;
		return false;
	}
	
	public void removeChessPieceFromBoard( int x, int y )
	{
		this.chessPiece[x][y] = null;
	}
	
	//move the chesss piece at its coordinate
	public void setChessPiece( int x, int y , ChessPiece chessPiece)
	{
		
		if( checkCoordinate(x, y))
		{	
			this.chessPiece[x][y] = chessPiece;
		}
		
	}
	
	public void copy( Board board)
	{
		this.width = board.width;
		this.height = board.height;
		this.chessPiece = new ChessPiece[8][8];
		for( int i = 0; i < board.width; i++ )
		{
			for( int j = 0; j < board.height; j ++)
				if( board.getFromBoard(i, j) != null)
					this.setChessPiece(i, j, board.getFromBoard(i, j));
		}
		
	}
	
	
	
	//find the position of the king with black or white
	//num[0] store the x position of king
	//num[1] store the y position of king
	private int[] findKing( int color, Board board)
	{
		int[] num = new int[2];
		
		for( int i = 0; i < this.width; i++ )
			for( int j = 0; j < this.height; j ++ )
				if( board.getFromBoard(i, j)!=null && board.getFromBoard(i, j).getName() == "king" && board.getFromBoard(i, j).getColor() == color)
				{
					num[0] = i;
					num[1] = j;
				}
	
		return num;
		
	}
	
	
	//see if the king will in check after my move
	//color is the color who is moving at this time
	public boolean inCheck(int color, Board board)
	{
		
		board.printBoard();
		System.out.println("check if in check");
		
		System.out.println(color);
		
		//get position of king
		int newColor = 0;
		if( color == 0 )
			newColor = 1;
		
		int[] num = findKing(newColor, board);
		int x = num[0];
		int y = num[1];
		
		System.out.println(x + " "+ y);
		
		for( int i = 0; i < width; i++ )
			for( int j = 0; j < height; j ++ )
					if( board.getFromBoard(i, j) != null )
					{	
						ChessPiece piece = board.getFromBoard(i, j);
						//check the king
						if( piece.getColor() == color )
						{
							Movement movement = new Movement(i, j, x, y);
							if( piece.canIMoveThisWay(movement, board ))
								return true;
						}
						
					}
		return false;
	}
	
	
	//color is the color to move
	public boolean somePicecCanKillTheKing( int color, Board newboard)
	{
		
		/*
		newboard.printBoard();
		System.out.println("");
		System.out.println(color);//white to move
		*/
		
		//board.printBoard();;
		//System.out.println("");
		//get position of king
		int newColor = 0;
		if( color == 0 )
			newColor = 1;
		
		int[] num = findKing(newColor, newboard);//find black king
		int x = num[0];
		int y = num[1];
		
		//System.out.println(x + " " + y);
		
		
		for( int i = 0; i < width; i++ )
			for( int j = 0; j < height; j ++ )
					if( newboard.getFromBoard(i, j) != null )
					{	
						ChessPiece piece = newboard.getFromBoard(i, j);
						//check the king
						if( piece.getColor() == color )
						{
							
							Movement movement = new Movement(i, j, x, y);
							if( piece.canIMoveThisWay(movement, newboard ))
								return true;
						}
						
					}
		return false;

	}
	
	
	//check mate, no matter how my king move, I die
	//color is my color to mvoe
	public boolean checkMate( int color, Board board)
	{
		//find my king
		int[] num = findKing(color, board );
		int x = num[0];
		int y = num[1];
		ChessPiece king = chessPiece[x][y];
		
		//my opponent's color
		int newColor = 0;
		if( color == 0 )
			newColor = 1;
		
		//System.out.println("King position is " + x + "," + y);

		
		//I'm in check
		if( inCheck( newColor, this  ) ){
			
			
			for( int i = x-1; i < x+2; i ++ )
			{
				for( int j = y-1; j < y+2; j ++ )
				{	
					Movement movement = new Movement(x, y, i, j);
					
					//System.out.println("the movement is from " + x + "," + y + "to" + i+ "," + j);
					
					//if I can move to this position
					if( king.canIMoveThisWay( movement, this ) )
					{
						Board newBoard = new Board();
						newBoard.copy(this);
						
						newBoard.removeChessPieceFromBoard(x, y);
						newBoard.setChessPiece(i, j,king);
						
						
						//if I move this way, then not check
						if( !somePicecCanKillTheKing( newColor, newBoard) )
						{
							//System.out.println( "move to" + i + " " + j);
							return false;
						}	
					}
				}
			}
			//after any move, still in check
			return true;
		}
		//not in check, not in checkMate
		return false;
	}
	
	
	//steel mate, if I move, I die
	//color is this color to move
	public boolean staleMate( int color , Board board)
	{
		
		board.printBoard();
		System.out.println("");
		System.out.println(color);//black to move
		
		
		
		/*
		//find my king
		int[] num = findKing(color, board);
		int x = num[0];
		int y = num[1];
		ChessPiece king = board.getFromBoard(x, y);
		
		System.out.println(x + " " + y);
		*/
		
		//my opponent's color
		int newColor = 0;
		if( color == 0 )
			newColor = 1;
		
		//I'm not in check
		if( !inCheck( newColor,board  ) ){
			
			
			for( int m = 0; m < board.getWidth(); m ++)
				for( int n = 0; n < board.getHeight(); n ++)
				{
					//my color to move
					if( board.getFromBoard(m, n).getColor() == color ){						
						System.out.println("stalemate " + " " + m + " " + n);
						for( int i = 0; i <  board.getWidth(); i ++ )
						{
							for( int j = 0; j < board.getHeight(); j ++ )
							{	
								//I move this piece
								ChessPiece piece =  board.getFromBoard(m, n);
								Movement movement = new Movement(m, n, i, j);
								//System.out.println("the movement is from " + x + "," + y + "to" + i+ "," + j);
			
								//if I can move to this position
								if( piece.canIMoveThisWay( movement, board ) )
								{
									Board newBoard = new Board();
									newBoard.copy(this);
									
									newBoard.removeChessPieceFromBoard(m, n);
									newBoard.setChessPiece(i, j,piece);
									//if I move this way, then not check
									
									if( !inCheck( newColor, newBoard) )
										return false;
								}
								
								//System.out.println("i'm good");
							}
						}
					}
				}
					//after any move, will cause check
				return true;
				
			}
			//in check, not in steelMate
			return false;
	}
	
	
	
	//print the board
	public void printBoard()
	{
		for( int i = 0; i < width; i ++ )
		{
			for( int j = 0; j < height; j ++ )
			{	
				if( chessPiece[i][j] != null )
				{
					ChessPiece piece = chessPiece[i][j];
					String name = null;
					if( piece.color == black )
						name = "black-" + piece.name + " ";
					else
						name = "white-" + piece.name + " ";
					System.out.print( name.substring(0, 10) + "  " );

				}
				else
					System.out.print( "empty       ");

			}
			System.out.println("");
			
		}
		
	}
	
	
}
