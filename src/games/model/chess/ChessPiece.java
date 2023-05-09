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
	
	protected boolean checkBasicMovement(int [] firstPos, int [] secondPos, boolean allowStraight, boolean allowDiagonal)
	{
		int rowDifference = secondPos[0] - firstPos[0];
		int colDifference = secondPos[1] - firstPos[1];
		
		if (allowDiagonal && (Math.abs(rowDifference) == Math.abs(colDifference) && rowDifference != 0) ||
			allowStraight && ((rowDifference == 0 || colDifference == 0) && rowDifference != colDifference))
		{
			ChessPiece target = null;
			int verticalDirection = 0;
			if (rowDifference != 0)
			{
				verticalDirection = rowDifference / Math.abs(rowDifference);
			}
			int horizontalDirection = 0;
			if (colDifference != 0)
			{
				horizontalDirection = colDifference / Math.abs(colDifference);
			}
			
			int row = firstPos[0] + verticalDirection;
			int col = firstPos[1] + horizontalDirection;
			
			while (row != secondPos[0])
			{
				int [] scanPosition = {row, col};
				target = getPieceAtPosition(scanPosition);
				
				if (!target.getClass().getSimpleName().equals("ChessEmpty"))
				{
					return false;
				}
				
				row += verticalDirection;
				col += horizontalDirection;
			}
			
			target = getPieceAtPosition(secondPos);
			if (target.getTeam() != team)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public abstract String getAsChar();
	
	public abstract boolean validateMove(int [] firstPos, int [] secondPos);
}
