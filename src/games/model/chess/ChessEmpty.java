package games.model.chess;

public class ChessEmpty extends ChessPiece
{
	private int team;
	
	public ChessEmpty(int team, ChessBoard board)
	{
		super(team, board);
		this.team = team;
	}
	
	public String getAsChar()
	{
		String letter = "e";
		
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
