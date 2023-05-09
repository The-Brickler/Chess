package games.controller;

import games.model.chess.ChessAI;
import games.model.chess.ChessBoard;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Controller
{
	private ChessAI ai;
	private ChessBoard board;
	
	public void start()
	{	
		board = new ChessBoard();
		ai = new ChessAI(this, board);
		
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		while (!input.equalsIgnoreCase("exit"))
		{
			System.out.println(board.getAsFormattedString());
			input = scanner.nextLine();
			
			board.makeMove(input);
		}
		
		ai.closeProcess();
		scanner.close();
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
	}
	
	private void makeAIMove() throws InterruptedException
	{
		board.makeMove(ai.getNextMove(1000));
		Thread.sleep(1500);
	}
	
	private void makePlayerMove(String move)
	{
		board.makeMove(move);
	}
}
