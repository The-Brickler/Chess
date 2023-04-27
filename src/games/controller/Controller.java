package games.controller;

import games.model.chess.ChessAI;
import games.model.chess.ChessBoard;

import javax.swing.JOptionPane;

public class Controller
{
	private ChessAI ai;
	private ChessBoard board;
	
	public void start()
	{	
		board = new ChessBoard();
		System.out.println(board.getAsFormattedString());
		
		ai = new ChessAI(this, board);
		
		board.makeMove("f2f4");
		
		System.out.println(board.getAsFormattedString());
		
		board.makeMove("a7a5");
		
		System.out.println(board.getAsFormattedString());
		
		System.out.println(ai.getNextMove());
		
		//System.out.println(board.getAsFormattedString());
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
	}
}
