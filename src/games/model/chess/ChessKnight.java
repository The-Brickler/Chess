package games.model.chess;

public class ChessKnight extends ChessPiece
{
	private int team;
	
	public ChessKnight(int team)
	{
		super(team);
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
		return false;
	}
}
