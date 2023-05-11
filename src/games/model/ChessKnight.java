package games.model;

public class ChessKnight extends ChessPiece
{
	private int team;
	
	public ChessKnight(int team, ChessBoard board)
	{
		super(team, board);
		this.team = team;
	}
	
	public String getAsChar()
	{
		String letter = "n";
		
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
		
		int moveDistance = Math.abs(rowDifference) + Math.abs(colDifference);
		
		if (moveDistance == 3 && Math.abs(rowDifference) < 3 && Math.abs(colDifference) < 3)
		{
			ChessPiece target = getPieceAtPosition(secondPos);
			if (target.getTeam() != team)
			{
				return true;
			}
		}
		
		return false;
	}
}
