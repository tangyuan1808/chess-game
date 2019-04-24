package lz.game;

import lz.controller.BoardController;
import lz.model.Board;

public class Game {

	public static void main(String[] args) {

		Board board = new Board();
		BoardController b= new BoardController(board);
		b.setVisible(true);
	}
	
	
}
