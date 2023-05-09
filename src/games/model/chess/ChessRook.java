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
			ChessPiece target = null;
			int direction = 0;
			int axis = 0;
			
			if (rowDifference != 0)
			{
				direction = rowDifference / Math.abs(rowDifference);
			}
			else
			{
				direction = colDifference / Math.abs(colDifference);
				axis = 1;
			}
			
			int index = firstPos[axis] + direction;
			
			while (index != secondPos[axis])
			{
				int [] scanPosition;
				
				if (axis == 0)
				{
					scanPosition = new int [] {index, firstPos[1]};
				}
				else
				{
					scanPosition = new int [] {firstPos[0], index};
				}
				
				target = getPieceAtPosition(scanPosition);
				
				if (!target.getClass().getSimpleName().equals("ChessEmpty"))
				{
					return false;
				}
				
				index += direction;
			}
			
			target = getPieceAtPosition(secondPos);
			if (target.getTeam() != team)
			{
				return true;
			}
		}
		
		return false;
	}
}
