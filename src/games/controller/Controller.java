package games.controller;

import games.model.chess.ChessAI;

import javax.swing.JOptionPane;

public class Controller
{
	private ChessAI ai;
	public void start()
	{
		ai = new ChessAI(this);
		ai.getNextMove();
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
	}
}
