package games.controller;

import games.model.chess.ChessAI;
import games.model.chess.ChessBoard;

import javax.swing.JOptionPane;

public class Controller
{
	private ChessAI whiteAI;
	private ChessAI blackAI;
	private ChessBoard board;
	
	public void start()
	{	
		board = new ChessBoard();
		System.out.println(board.getAsFormattedString());
		
		whiteAI = new ChessAI(this, board, "w");
		blackAI = new ChessAI(this, board, "b");
		
		int counter = 0;
		
		while (counter < 100)
		{
			board.makeMove(whiteAI.getNextMove());
			System.out.println(board.getAsFormattedString());
			board.makeMove(blackAI.getNextMove());
			System.out.println(board.getAsFormattedString());
			
			counter++;
		}
		
		//System.out.println(board.getAsFormattedString());
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
	}
}
