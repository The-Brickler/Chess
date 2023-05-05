package games.model.chess;

import java.io.IOException;

import games.controller.Controller;
import games.controller.ProcessController;

public class ChessAI
{
	
	private Controller app;
	private ChessBoard board;
	
	private ProcessController process;
	
	public ChessAI(Controller app, ChessBoard board)
	{
		this.app = app;
		this.board = board;
		
		process = new ProcessController("./src/stockfish/15.1/bin/stockfish", app);
		process.start();
	}
	
	public String getNextMove()
	{
		
		return "";
	}
	
	
	

}
