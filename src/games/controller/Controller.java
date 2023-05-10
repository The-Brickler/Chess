package games.controller;

import games.model.chess.ChessAI;
import games.model.chess.ChessBoard;
import games.view.ChessFrame;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Controller
{
	private ChessAI ai;
	private ChessBoard board;
	
	private ChessFrame frame;
	
	public void start()
	{	
		/*
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
		*/
		
		frame = new ChessFrame(this);
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
