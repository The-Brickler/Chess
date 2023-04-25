package games.model.chess;

public class ChessBoard
{
	ChessPiece [][] board;
	
	public ChessBoard()
	{
			board = new ChessPiece [8][8];
			setupBoard();
	}
	
	public String getAsFEN()
	{
		return "";
	}
	
	private void setupBoard()
	{
		
	}
	
	
	
}
