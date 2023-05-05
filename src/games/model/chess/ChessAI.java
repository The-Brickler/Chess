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
	
	/**
	 * Calculates the best move for the ai to make based of the current board
	 * 
	 * @param waitTime
	 * 					
	 * @return
	 */
	public String getNextMove(int waitTime)
	{
		String fen = board.getAsFEN();
		
		process.sendCommand("position fen " + fen);
		process.sendCommand("go movetime " + waitTime);
		
		String output = process.getOutput(waitTime + 100);
		output = output.split("bestmove ")[1]; //gets everything after "bestmove "
		output = output.split(" ")[0]; //get everything before the next space
		
		return output;
	}
	
	public void closeProcess()
	{
		process.stop();
	}
	
	
	

}
