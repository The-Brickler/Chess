package games.model.chess;

public class ChessBishop extends ChessPiece
{
	private int team;
	
	public ChessBishop(int team)
	{
		super(team);
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
}
