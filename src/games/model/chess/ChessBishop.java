package games.model.chess;

public class ChessBishop extends ChessPiece
{
	private int team;
	
	public ChessBishop(int team, ChessBoard board)
	{
		super(team, board);
		this.team = team;
	}
	
	public String getAsChar()
	{
		String letter = "b";
		
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
		
		if (Math.abs(rowDifference) == Math.abs(colDifference) && rowDifference != 0)
		{
			ChessPiece target = null;
			int verticalDirection = rowDifference / Math.abs(rowDifference);
			int horizontalDirection = colDifference / Math.abs(colDifference);
			
			int row = firstPos[0] + verticalDirection;
			int col = firstPos[1] + horizontalDirection;
			
			while (row != secondPos[0])
			{
				int [] scanPosition = {row, col};
				target = getPieceAtPosition(scanPosition);
				
				if (!target.getClass().getSimpleName().equals("ChessEmpty"))
				{
					return false;
				}
				
				row += verticalDirection;
				col += horizontalDirection;
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
