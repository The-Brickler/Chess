package games.model;

public class ChessKing extends ChessPiece
{
private int team;
	
	public ChessKing(int team, ChessBoard board)
	{
		super(team, board);
		this.team = team;
	}
	
	public String getAsChar()
	{
		String letter = "k";
		
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
		
		if (Math.abs(rowDifference) < 2 && Math.abs(colDifference) < 2)
		{
			return checkBasicMovement(firstPos, secondPos, true, true);
		}
		return false;
	}
}
