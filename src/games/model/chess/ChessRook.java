package games.model.chess;

public class ChessRook extends ChessPiece
{
	private int team;
	
	public ChessRook(int team)
	{
		super(team);
		this.team = team;
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
}
