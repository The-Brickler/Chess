package games.model.chess;

public class ChessPawn extends ChessPiece
{
	
	public ChessPawn(int team, ChessBoard board)
	{
		super(team, board);
	}
	
	public String getAsChar()
	{
		String letter = "p";
		
		if (team == ChessPiece.WHITE)
		{
			letter = letter.toUpperCase();
		}
		
		return letter;
	}

	@Override
	public boolean validateMove(int[] firstPos, int[] secondPos)
	{
		int rowDifference = secondPos[0] - firstPos[0];
		int colDifference = secondPos[1] - firstPos[1];
		ChessPiece target = getPieceAtPosition(secondPos);
		
		
		if (Math.abs(colDifference) > 0 && Math.abs(colDifference) <= 1 && Math.abs(colDifference) == 1) // check for taking pieces diagonally
		{	
			if ((rowDifference > 0 && team == BLACK) || (rowDifference < 0 && team == WHITE))
			{
				if (!target.getClass().getSimpleName().equals("ChessEmpty") && target.getTeam() != team)
				{
					return true;
				}
			}
		}
		else if (colDifference == 0) //vertical movement
		{
			if ((rowDifference > 0 && team == BLACK) || (rowDifference < 0 && team == WHITE))
			{
				if (Math.abs(rowDifference) == 2)
				{
					if (moveCount == 0)
					{
						ChessPiece between = getPieceAtOffset(rowDifference / Math.abs(rowDifference), 0);
						if (between.getClass().getSimpleName().equals("ChessEmpty"))
						{
							return true;
						}
					}
				}
				else if (Math.abs(rowDifference) == 1 && (target.getClass().getSimpleName().equals("ChessEmpty")))
				{
						return true;
				}
				
				
			}
		}
		return false;
	}
	
	@Override
	public boolean canPromote()
	{
		int row = super.findPosition()[0];
		
		if ((row == 0 && super.team == super.BLACK) || (row == 7 && super.team == super.WHITE))
		{
			return true;
		}
		
		return false;
	}
}
