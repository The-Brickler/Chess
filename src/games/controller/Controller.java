package games.controller;

import games.model.ChessAI;
import games.model.ChessBoard;
import games.model.ChessPiece;
import games.view.ChessFrame;
import games.view.ChessPanel;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Controller
{
	private ChessAI ai;
	private ChessBoard board;
	
	private ChessFrame frame;
	private ChessPanel panel;
	
	public void start()
	{	
		
		board = new ChessBoard();
		
		ai = new ChessAI(this, board);
		
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		/*
		while (!input.equalsIgnoreCase("exit"))
		{
			System.out.println(board.getAsFormattedString());
			input = scanner.nextLine();
			if (!input.equalsIgnoreCase("exit"))
			{		
				board.makeMove(input);
			}
			
		}
		*/
		
		//ai.closeProcess();
		scanner.close();
		
		frame = new ChessFrame(this);
		panel = (ChessPanel) frame.getContentPane();
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
	}
	
	public void AIMove() throws InterruptedException
	{
		String nextMove = ai.getNextMove(1000);
		if (nextMove.equals("gameover"))
		{
			frame.gameEnd();
		}
		else
		{
			board.makeMove(ai.getNextMove(500));
			Thread.sleep(1500);
		}
	}
	
	public void playerMove(int firstRow, int firstCol, int secondRow, int secondCol)
	{
		int modFirstRow = Math.abs(firstRow - 8);
		int modSecondRow = Math.abs(secondRow - 8);
		
		char firstColChar = (char) ('a' + firstCol);
		char secondColChar = (char) ('a' + secondCol);
		
		String move = "" + firstColChar + modFirstRow + secondColChar + modSecondRow;
		System.out.println(move);
		board.makeMove(move);
		panel.setCanMove(false);
		panel.updateDisplay();
		
		if (board.getPieceAt(new int [] {secondRow, secondCol}).canPromote())
		{
			panel.setStatus("Select a promotion");
			panel.setCanMove(false);
			frame.showPromoteDialog(secondRow, secondCol);
		}
		else
		{
			frame.madeMove();
		}
		
	}
	
	public String getImageFor(int row, int col)
	{
		String imageName = "";
		
		ChessPiece piece = board.getPieceAt(new int [] {row, col});
		if (piece.getTeam() != ChessPiece.NOTEAM)
		{
			if (piece.getTeam() == ChessPiece.WHITE)
			{
				imageName += "white_";
			}
			else
			{
				imageName += "black_";
			}
			
			imageName += piece.getAsChar().toLowerCase();
		}
		
		return imageName;
	}
	
	public boolean validate(int firstRow, int firstCol, int secondRow, int secondCol)
	{
		int [] firstPos = {firstRow, firstCol};
		int [] secondPos = {secondRow, secondCol};
		
		return board.getPieceAt(firstPos).validateMove(firstPos, secondPos);
	}

	public void promote(int[] nextPromotion, String newPiece)
	{
		board.promotePieceAt(nextPromotion, newPiece);
		
	}
	
	public void reset()
	{
		board = new ChessBoard();
		ai.closeProcess();
		ai = new ChessAI(this, board);
	}
}
