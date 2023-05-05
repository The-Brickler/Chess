package games.model.chess;

public class ChessQueen extends ChessPiece
{
private int team;
	
	public ChessQueen(int team, ChessBoard board)
	{
		super(team, board);
		this.team = team;
	}
	
	public String getAsChar()
	{
		String letter = "q";
		
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
