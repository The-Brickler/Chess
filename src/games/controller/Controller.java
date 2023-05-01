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
		ai = new ChessAI(this, board);
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
	}
}
