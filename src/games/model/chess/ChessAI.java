package games.model.chess;

import java.io.IOException;

import games.controller.Controller;

public class ChessAI
{
	
	private Controller app;
	private ChessBoard board;
	
	private Process stockFish;
	
	public ChessAI(Controller app, ChessBoard board)
	{
		this.app = app;
		this.board = board;
		
		try
		{
			stockFish = new ProcessBuilder("./src/stockfish/15.1/bin/stockfish").start();
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
	}
	
	public String getNextMove()
	{
		System.out.println(stockFish.getOutputStream());
		
		return "";
	}
	
	
	

}
