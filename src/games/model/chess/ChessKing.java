package games.model.chess;

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
		return false;
	}
}
