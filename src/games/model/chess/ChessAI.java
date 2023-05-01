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
		
		process = new ProcessController(app, "./src/stockfish/15.1/bin/stockfish");
	}
	
	public String getNextMove() throws InterruptedException
	{
		process.sendCommand("ucinewgame");
		Thread.sleep(1500);
		process.sendCommand("go movetime 1000");
		
		//System.out.println(process.getLine());
		Thread.sleep(1500);

		System.out.println(process.getLine());
		
		return "";
	}
	
	
	

}
