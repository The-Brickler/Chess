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
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[0].length; col++)
			{
				int team = ChessPiece.BLACK;
				if (row > board.length/2)
				{
					team = ChessPiece.WHITE;
				}
				
				if (row == 1 || row == 6)
				{
					board[row][col] = new ChessPawn(team);
				}
				else if (row == 0 || row == 7)
				{
					switch (col)
					{
					case 0:
					case 7:
						board[row][col] = new ChessRook(team);
						break;
					case 1:
					case 6:
						board[row][col] = new ChessKnight(team);
						break;
					case 2:
					case 5:
						board[row][col] = new ChessBishop(team);
						break;
					case 3:
						board[row][col] = new ChessKing(team);
						break;
					case 4:
						board[row][col] = new ChessQueen(team);
						break;
					default:
						board[row][col] = new ChessEmpty(ChessPiece.NOTEAM);
					}
				}
				else
				{
					board[row][col] = new ChessEmpty(ChessPiece.NOTEAM);
				}
			}
		}
	}
	
	
	
}
