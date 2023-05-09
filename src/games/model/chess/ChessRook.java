package games.model.chess;

public class ChessRook extends ChessPiece
{
	
	public ChessRook(int team, ChessBoard board)
	{
		super(team, board);
	}
	
	public String getAsChar()
	{
		String letter = "r";
		
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
		
		if ((rowDifference == 0 || colDifference == 0) && rowDifference != colDifference)
		{
			ChessPiece target = super.getPieceAtPosition(secondPos);
			
			if (target.getTeam() == super.team)
			{
				return false;
			}
			
			else if (Math.abs(rowDifference) == 1 || Math.abs(colDifference) == 1)
			{
				return true;
			}
			
			
			int moveDirection = 0;
			int axis = 0;
			if (rowDifference == 0 && colDifference != 1)
			{
				moveDirection = colDifference / Math.abs(colDifference);
				axis = 1;
			}
			else if (colDifference == 0 && rowDifference != 1)
			{
				moveDirection = rowDifference / Math.abs(rowDifference);
			}
			
			for (int index = firstPos[axis] + moveDirection; index != secondPos[axis]; index += moveDirection)
			{
				int [] scanPosition = new int[2];
				
				if (axis == 0)
				{
					scanPosition = new int [] {index, firstPos[1]};
				}
				else
				{
					scanPosition = new int [] {firstPos[0], index};
				}
				
				ChessPiece scanPiece = super.getPieceAtPosition(scanPosition);
				if (!scanPiece.getClass().getSimpleName().equals("ChessEmpty"))
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
}
