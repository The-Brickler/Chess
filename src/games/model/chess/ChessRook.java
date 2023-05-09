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
		return checkBasicMovement(firstPos, secondPos, true, false);
	}
}
