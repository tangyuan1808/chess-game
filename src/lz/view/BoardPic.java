package lz.view;

import javax.swing.ImageIcon;

import lz.model.Board;
import lz.model.ChessPiece;

public class BoardPic {

	public final static int black = 0;
	public final static int white = 1;	

	private ImageIcon pic[][];
	
	public BoardPic(Board board){
	
	   //String address = "/Users/liangzhang/Documents/workspace/MyChessGame/Images";	
	   int width = board.getWidth();
	   int height = board.getHeight();
	   pic = new ImageIcon[width][height];
	   
	   for( int i = 0; i < width; i ++ )
		   for( int j = 0; j < height; j ++ ){
			   
			   if( board.getFromBoard(i, j) != null)
			   {
				   ChessPiece chessPiece = board.getFromBoard(i, j);
				   if( chessPiece.getColor() == black )
				   {
					   //System.out.println("black"+chessPiece.getName()+".png");
					   //pic[i][j] = new ImageIcon( address+"black"+chessPiece.getName()+".png" );
					   pic[i][j] = new ImageIcon(getClass().getResource("black"+chessPiece.getName()+".png"));
					   //System.out.println(pic[i][j]);
				   }
				   else
				   {	
					   //System.out.println("white"+chessPiece.getName()+".png");
					   //pic[i][j] = new ImageIcon(getClass().getResource("whiterook.png"));
					   pic[i][j] = new ImageIcon(getClass().getResource("white"+chessPiece.getName()+".png"));
					   //pic[i][j] = new ImageIcon( address+"white"+chessPiece.getName()+".png" );

				   }			
			   }   
		   }
	}
	
	public ImageIcon getIconFromBoardPic( int x, int y)
	{
		return this.pic[x][y];
		
	}
}
	

