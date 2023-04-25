package games.model.chess;

public abstract class ChessPiece
{
	public final static int BLACK = -1;
	public final static int WHITE = 1;
	public final static int NOTEAM = 0;
	
	private int team;
	
	public ChessPiece(int team)
	{
		this.team = team;
	}
	
	public abstract String getAsChar();
}
