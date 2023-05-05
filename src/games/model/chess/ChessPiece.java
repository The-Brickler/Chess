package games.model.chess;

public abstract class ChessPiece
{
	public final static int BLACK = -1;
	public final static int WHITE = 1;
	public final static int NOTEAM = 0;
	
	protected int team;
	protected int moveCount;
	
	private ChessBoard board;
	
	public ChessPiece(int team, ChessBoard board)
	{
		this.team = team;
		moveCount = 0;
		this.board = board;
	}
	
	public void madeMove()
	{
		moveCount++;
	}
	
	public int getTeam()
	{
		return team;
	}
	
	public boolean canPromote()
	{
		return false;
	}
	
	protected int [] findPosition()
	{
		return board.getPositionOfPiece(this);
	}
	
	protected ChessPiece getPieceAtOffset(int rowOffset, int colOffset)
	{
		ChessPiece piece = null;
		
		int [] position = findPosition();
		
		position[0] += rowOffset;
		position[1] += colOffset;
		
		piece = board.getPieceAt(position);
		
		return piece;
	}
	
	protected ChessPiece getPieceAtPosition(int [] position)
	{
		return board.getPieceAt(position);
	}
	
	public abstract String getAsChar();
	
	public abstract boolean validateMove(int [] firstPos, int [] secondPos);
}
