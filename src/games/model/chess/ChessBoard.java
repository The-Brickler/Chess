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
					board[row][col] = new ChessPawn(team, this);
				}
				else if (row == 0 || row == 7)
				{
					switch (col)
					{
					case 0:
					case 7:
						board[row][col] = new ChessRook(team, this);
						break;
					case 1:
					case 6:
						board[row][col] = new ChessKnight(team, this);
						break;
					case 2:
					case 5:
						board[row][col] = new ChessBishop(team, this);
						break;
					case 3:
						board[row][col] = new ChessKing(team, this);
						break;
					case 4:
						board[row][col] = new ChessQueen(team, this);
						break;
					default:
						board[row][col] = new ChessEmpty(ChessPiece.NOTEAM, this);
					}
				}
				else
				{
					board[row][col] = new ChessEmpty(ChessPiece.NOTEAM, this);
				}
			}
		}
	}
	
	public void makeMove(String move)
	{
		int firstRow = board.length - Integer.parseInt(move.substring(1, 2));
		int firstCol = letterToNumber(move.charAt(0));
		int secondRow = board.length - Integer.parseInt(move.substring(3));
		int secondCol = letterToNumber(move.charAt(2));
		
		ChessPiece target = board[firstRow][firstCol];
		
		int [] firstPos = {firstRow, firstCol};
		int [] secondPos = {secondRow, secondCol};
		
		if (target.validateMove(firstPos, secondPos))
		{
			System.out.println("VALID");
			board[secondRow][secondCol] = target;
			board[firstRow][firstCol] = new ChessEmpty(ChessPiece.NOTEAM, this);
		}
		else
		{
			System.out.println("INVALID");
		}
	}
	
	public int [] getPositionOfPiece(ChessPiece piece)
	{
		int position [] = {-1, -1};
		
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				if (board[row][col].equals(piece))
				{
					position[0] = row;
					position[1] = col;
				}
			}
		}
		
		return position;
	}
	
	public ChessPiece getPieceAt(int [] position)
	{
		//Bounds Check
		if (position[0] >= board.length || position[0] < 0 || position[1] >= board[0].length || position[1] < 0)
		{
			return null;
		}
		
		return board[position[0]][position[1]];
	}
	
	public boolean promotePieceAt(int [] position, String newPiece)
	{
		if (board[position[0]][position[1]].canPromote())
		{
			int team = board[position[0]][position[1]].getTeam();
			if (newPiece.equals("queen"))
			{
				board[position[0]][position[1]] = new ChessQueen(team, this);
			}
			if (newPiece.equals("rook"))
			{
				board[position[0]][position[1]] = new ChessRook(team, this);
			}
			if (newPiece.equals("knight"))
			{
				board[position[0]][position[1]] = new ChessKnight(team, this);
			}
			if (newPiece.equals("bishop"))
			{
				board[position[0]][position[1]] = new ChessBishop(team, this);
			}
		}
		
		return false;
	}
	
	private int letterToNumber(char letter)
	{
		int number = letter - 'a';
		return number;
	}
	
}
