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
		int rowDifference = Math.abs(firstPos[0] - secondPos[0]);
		int colDifference = Math.abs(firstPos[1] - secondPos[1]);
		ChessPiece target = super.getPieceAtPosition(secondPos);
		
		if (colDifference > 0 && colDifference <= 1 && rowDifference == 1) // check for taking pieces diagonally
		{	
			if (!target.getClass().getSimpleName().equals("ChessEmpty") && target.getTeam() != super.team)
			{
				return true;
			}
		}
		else if (colDifference == 0) //check that there's nothing in front for moving straight
		{
			if (target.getClass().getSimpleName().equals("ChessEmpty"))
			{
				if ((rowDifference == 1) || (rowDifference == 2 && super.moveCount == 0))
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
