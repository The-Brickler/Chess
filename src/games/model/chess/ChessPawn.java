package games.model.chess;

public class ChessPawn extends ChessPiece
{
	private int team;
	
	public ChessPawn(int team)
	{
		super(team);
		this.team = team;
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
}
