package games.model.chess;

import java.io.IOException;

import games.controller.Controller;

public class ChessAI
{
	
	private Controller app;
	private ChessBoard board;
	
	public ChessAI(Controller app, ChessBoard board)
	{
		this.app = app;
		this.board = board;
	}
	
	public String getNextMove()
	{
		return "";
	}
	
	
	

}
