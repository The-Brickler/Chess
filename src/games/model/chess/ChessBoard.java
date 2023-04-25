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
		String fen = "";
		
		for (int row = 0; row < board.length; row++)
		{
			int numEmpty = 0;
			for (int col = 0; col < board[0].length; col++)
			{
				if (board[row][col].getAsChar().equalsIgnoreCase("e"))
				{
					numEmpty++;
				}
				else
				{
					if (numEmpty > 0)
					{
						fen += numEmpty;
						numEmpty = 0;
					}
					fen += board[row][col].getAsChar();
				}
			}
			if (numEmpty > 0)
			{
				fen += numEmpty;
			}
			if (row != board.length - 1)
			{
				fen += "/";
			}
			
		}
		
		return fen;
	}
	
	public String getAsFormattedString()
	{
		String output = "";
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[0].length; col++)
			{
				String pieceChar = board[row][col].getAsChar();
				if (pieceChar.equalsIgnoreCase("e"))
				{
					pieceChar = " ";
				}
				output += "[ " + pieceChar + " ] ";
			}
			output += "\n";
		}
		return output;
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
	
	public void makeMove(String move)
	{
		int firstRow = Integer.parseInt(move.substring(1, 2));
		int firstCol = board[0].length - letterToNumber(move.charAt(0));
		int secondRow = Integer.parseInt(move.substring(3));
		int secondCol = board[0].length - letterToNumber(move.charAt(2));
		
		ChessPiece target = board[firstRow][firstCol];
		
		board[secondRow][secondCol] = target;
		board[firstRow][firstCol] = new ChessEmpty(ChessPiece.NOTEAM);
	}
	
	private int letterToNumber(char letter)
	{
		int number = letter;
		return number;
	}
	
}
