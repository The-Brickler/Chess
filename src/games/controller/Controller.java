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
		System.out.println(board.getAsFEN());
		
		ai = new ChessAI(this, this.board);
		System.out.println(ai.getNextMove());
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
	}
}
